#!/usr/bin/awk -f

{
	if(NR == 1)
	{
		{print $0}
	}
	else 
	{
		if($0 ~ /^[[:digit:]]/)
		{
			{printf "\n" $0}
		}
		else
		{
			{printf " " $0}
		}
	}

}
