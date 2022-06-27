#!/bin/bash

echo "Welcome to the Dendron Programming Language demo!"
echo

echo "Select demo type:"
echo
options=("Test" "Interactive" "Quit")
select opt in "${options[@]}"
do
	case $opt in
		"Test")
			manual_mode=false
			break
			;;
		"Interactive")
			manual_mode=true
			break
			;;
		"Quit")
			exit
			;;
		*)
			echo "Invalid Option"
			;;
	esac
done

if( [ -d "dendron" ] && [ -f "DendronTest.class" ] )
then
		echo
		echo "Files already compiled, would you like to recompile [ (y)es / (n)o ]?"
		read answer
		echo

		if [ ${answer,,} == "y" ] || [ ${answer,,} == "yes" ]
		then
			rm *.class dendron/*.class dendron/machine/*.class dendron/tree/*.class
			rmdir dendron/machine dendron/tree dendron
			javac -d . src/dendron/*.java src/dendron/machine/*.java src/dendron/tree/*.java src/DendronTest.java
		fi
else
		javac -d . src/dendron/*.java src/dendron/machine/*.java src/dendron/tree/*.java src/DendronTest.java
fi


if [ $manual_mode == true ]
then
		java DendronTest
else
		loop_menu=true
		while [ $loop_menu == true ]
		do
		echo "Select function to demo:"
		echo
			options=("Quick Test" "Assignment" "Print" "Quit")
			select opt in "${options[@]}"
			do
				case $opt in
					"Quick Test")
						java dendron.machine.InstructionReader assy/test1.denm
						break
						;;
					"Assignment")
						assignment=true
						. ./operation_selector.sh
						break
						;;
					"Print")
						assignment=false
						. ./operation_selector.sh
						break
						;;
					"Quit")
						loop_menu=false
						break
						;;
					*)
						echo "Invalid Option"
						;;
				esac
			done
			echo
		done
fi

echo "Thank you for testing the Dendron demo!"