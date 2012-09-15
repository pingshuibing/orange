function HelperFunctions()
{

    //fills a dropdown given tha parameters
    this.fillSelect =  function fillSelect(min, max, interval, $element)
    {
        $element.find('option').remove().end();

        $element.append(
            $('<option>').attr('value', '').text('None')
        );

        for (i = min; i <= max; i += interval)
        {
            $element.append(
                $('<option>').attr('value', i).text(i)
            );
        }
    }

    //creates a td element with a given value
    this.createCell = function createCell(dataValue)
    {
        return $('<td>').text(dataValue);
    }

    //returns a data value given an xml row
    this.readValueFromXml = function readValueFromXml($xmlRow, propertyName)
    {
        return $xmlRow.find(propertyName).text();
    }

    //rounds up a number to the given number of decimals
    this.roundNumber = function roundNumber(num, dec)
    {
        var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
        return result;
    }

}