#!/bin/bash

if [ $assignment == true ]
then
		path="assy/asgn-"
else
		path="assy/print-"
fi

echo
echo "Select type to demo:"
echo
choices=("Addition" "Subtraction" "Multiplication" "Division" "Square Root" "Constant" "Big Constant" "Negative Constant" "Variable" "Big Variable" "Negative Variable" "Back" "Quit")
loop_menu_two=true
while [ $loop_menu_two == true ]
do
	select choice in "${choices[@]}"
	do
		case $choice in
			"Addition")
				java dendron.machine.InstructionReader ${path}sum.denm
				break
				;;
			"Subtraction")
				java dendron.machine.InstructionReader ${path}dif.denm
				break
				;;
			"Multiplication")
				java dendron.machine.InstructionReader ${path}prod.denm
				break
				;;
			"Division")
				java dendron.machine.InstructionReader ${path}quot.denm
				break
				;;
			"Square Root")
				java dendron.machine.InstructionReader ${path}sqrt.denm
				break
				;;
			"Constant")
				java dendron.machine.InstructionReader ${path}const.denm
				break
				;;
			"Big Constant")
				java dendron.machine.InstructionReader ${path}big-const-expr.denm
				break
				;;
			"Negative Constant")
				java dendron.machine.InstructionReader ${path}neg-const.denm
				break
				;;
			"Variable")
				java dendron.machine.InstructionReader ${path}var.denm
				break
				;;
			"Big Variable")
				java dendron.machine.InstructionReader ${path}big-var-expr.denm
				break
				;;
			"Negative Variable")
				java dendron.machine.InstructionReader ${path}neg-var.denm
				break
				;;
			"Back")
				loop_menu_two=false
				break
				;;
			"Quit")
				loop_menu=false
				loop_menu_two=false
				break
				;;
			*)
				echo "Invalid Option"
				;;
		esac
	done
	echo
done