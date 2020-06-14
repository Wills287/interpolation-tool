package wills.interpolation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wills.interpolation.helper.DataProcessingHelper;

@Component
public class DataProcessingService {

    private final DataProcessingHelper helper;

    @Autowired
    public DataProcessingService(DataProcessingHelper helper) {
        this.helper = helper;
    }

    public String[][] processDataset(String[][] input) {
        String[][] output = new String[input.length][input[0].length];

        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[row].length; column++) {
                String value = input[row][column];

                if ("nan".equalsIgnoreCase(input[row][column])) {
                    value = String.valueOf(
                            helper.getAverage(
                                    helper.getAdjacentPoints(row, column, input),
                                    input
                            )
                    );
                }
                output[row][column] = value;
            }
        }

        return output;
    }
}
