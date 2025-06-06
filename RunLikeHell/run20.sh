#!/bin/bash

# Sean Szumlanski
# COP 3503, Spring 2023

# =======================
# RunLikeHell: run20.sh
# =======================
# You can run this script at the command line like so:
#
#   bash run20.sh
#
# For more details, see the assignment PDF.


################################################################################
# Shell check.
################################################################################

# Running this script with sh instead of bash can lead to false positives on the
# test cases. Yikes! These checks ensure the script is not being run through the
# Bourne shell (or any shell other than bash).

if [ "$BASH" != "/bin/bash" ] && [ "$BASH" != "/usr/bin/bash" ]; then
  echo ""
  echo " Bloop! Please use bash to run this script, like so: bash test-all.sh"
  echo ""
  exit
fi

if [ -z "$BASH_VERSION" ]; then
  echo ""
  echo " Bloop! Please use bash to run this script, like so: bash test-all.sh"
  echo ""
  exit
fi


################################################################################
# Initialization.
################################################################################

PASS_CNT=0
NUM_TEST_CASES=20


################################################################################
# Check that all required files are present.
################################################################################

if [ ! -f RunLikeHell.java ]; then
	echo ""
	echo " Error: You must place RunLikeHell.java in this directory before we"
	echo "        can proceed. Aborting test script."
	echo ""
	exit
elif [ ! -f Solution.java ]; then
	echo ""
	echo " Error: You must place Solution.java in this directory before we"
	echo "        can proceed. Aborting test script."
	echo ""
	exit
elif [ ! -d sample_output ]; then
	echo ""
	echo " Error: You must place the sample_output folder in this directory"
	echo "        before we can proceed. Aborting test script."
	echo ""
	exit
fi

function check_test_case()
{
	local testcase=$1

	if [ ! -f TestCase$testcase.java ]; then
		echo ""
		echo " Error: You must place TestCase$testcase.java in this directory before we"
		echo "        can proceed. Aborting test script."
		echo ""
		exit
	fi
	if [ ! -f sample_output/TestCase$testcase-output.txt ]; then
		echo ""
		echo " Error: You must place TestCase$testcase-output.txt in the sample_output directory"
		echo "        before we can proceed. Aborting test script."
		echo ""
		exit
	fi
}

for i in `seq -f "%02g" 1 $NUM_TEST_CASES`;
do
	check_test_case "$i"
done


################################################################################
# Function for running a single test case.
################################################################################

function run_test_case()
{
	local testcase=$1

	echo -n "  [Test Case] $testcase.java ... "

	rm *.class 2> /dev/null

	# Attempt to compile.
	javac RunLikeHell.java $testcase.java 2> /dev/null
	compile_val=$?
	if [[ $compile_val != 0 ]]; then
		echo "fail (failed to compile)"
		return
	fi

	# Run program. Capture return value to check whether it crashes.
	java $testcase > myoutput.txt 2> /dev/null
	execution_val=$?
	if [[ $execution_val != 0 ]]; then
		echo "fail (program crashed)"
		return
	fi

	# Run diff and capture its return value.
	diff myoutput.txt sample_output/$testcase-output.txt > /dev/null
	diff_val=$?
	
	# Output results based on diff's return value.
	if  [[ $diff_val != 0 ]]; then
		echo "fail (output does not match)"
	else
		echo "PASS!"
	fi
}


################################################################################
# Run test cases.
################################################################################

echo ""
echo "================================================================"
echo "Running test cases..."
echo "================================================================"
echo ""

for i in `seq -f "%02g" 1 $NUM_TEST_CASES`;
do
	run_test_case "TestCase$i" 2> /dev/null
done


############################################################################
# Check for tabs vs. spaces.
############################################################################

echo ""
echo "================================================================"
echo "Checking for tabs vs. spaces..."
echo "================================================================"
echo ""

if ! [ -x "$(command -v grep)" ]; then
	echo "  Skipping tabs vs. spaces check; grep not installed. You"
	echo "  might see this message if you're running this script on a"
	echo "  Mac. Please be sure to test your final code on Eustis."
elif ! [ -x "$(command -v awk)" ]; then
	echo "  Skipping tabs vs. spaces check; awk not installed. You"
	echo "  might see this message if you're running this script on a"
	echo "  Mac. Please be sure to test your final code on Eustis."
else
	num_spc_lines=`grep "^ " RunLikeHell.java | wc -l | awk '{$1=$1};1'`
	num_tab_lines=`grep "$(printf '^\t')" RunLikeHell.java | wc -l | awk '{$1=$1};1'`
	num_und_lines=`grep "$(printf '^[^\t ]')" RunLikeHell.java | wc -l | awk '{$1=$1};1'`

	echo "  Number of lines beginning with spaces: $num_spc_lines"
	echo "  Number of lines beginning with tabs: $num_tab_lines"
	echo "  Number of lines with no indentation: $num_und_lines"

	if [ "$num_spc_lines" -gt 0 ] && [ "$num_tab_lines" -gt 0 ]; then
		echo ""
		echo "  Whoa, buddy! It looks like you're starting some lines of code with"
		echo "  tabs, and other lines of code with spaces. You'll need to choose"
		echo "  one or the other."
		echo ""
		echo "  Try running the following commands to see which of your lines begin"
		echo "  with spaces (the first command below) and which ones begin with tabs"
		echo "  (the second command below):"
		echo ""
		echo "     grep \"^ \" RunLikeHell.java -n"
		echo "     grep \"\$(printf '^\t')\" RunLikeHell.java -n"
	elif [ "$num_spc_lines" -gt 0 ]; then
		echo ""
		echo "  Looks like you're using spaces for all your indentation! (Yay!)"
	elif [ "$num_tab_lines" -gt 0 ]; then
		echo ""
		echo "  Looks like you're using tabs for all your indentation! (Yay!)"
	else
		echo ""
		echo "  Whoa, buddy! It looks like none of your lines of code are indented!"
	fi
fi


################################################################################
# Cleanup phase.
################################################################################

rm -f *.class
rm -f myoutput.txt


################################################################################
# Final thoughts.
################################################################################

echo ""
echo "================================================================"
echo "Final Thoughts"
echo "================================================================"
echo ""
echo "  Recall that these are not the same test cases released with"
echo "  the assignment PDF."
echo ""
echo "  Also, this script does not impose any runtime restrictions and"
echo "  therefore won't be able to detect any proccess timeouts that"
echo "  might be indicated in your official test case transcript."
echo ""
