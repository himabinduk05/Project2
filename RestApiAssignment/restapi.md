Project-2

This project contains
	i. RestApi.war - Executable which is hosted on server
	ii.RestApi - Project source and related dependent files 
		                     that are used in this project
	iii.readme.txt  - Describing the contents of the current commit

Project Descrption:

1)This project is used to develop Restful WebService to host weather information.It is used to implement POST,GET,DELETE operations using Restful endpoints
a)/historical/-endpoint is used to implement GET method which list of all dates for which weather information is available in Jason array format.
b)/historical/<dateYYYYMMDD> is used to implement GET method which displays  information for a particular date given in yyyyddmm format.
c)/historical/-endpoint is used to implement POST method which Add weather information for a particular day if it is not available.If date is already available then it updates 
weather data for given date.Here input should be given in JSCON format to the post method.
d)/historical/<dateYYYYMMDD>-end point is used to implement DELETE method which deletes weather info a particular date.Here date should be given in yyyyddmm
