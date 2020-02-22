echo -n "first number : "
read first
echo -n "second number : "
read second
echo -n "third number : "
read third


big=0
small=0


if [ $first -gt $second ]
then
    big=$first
    small=$second
else
    big=$second
    small=$first
fi

if [ $third -gt $big ]; then
    big=$third
fi

if [ $small -gt $third ]; then
    small=$third
fi

echo -e Biggest Number is $big, Small Number is $small
