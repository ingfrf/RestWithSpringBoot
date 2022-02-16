package com.enmivida.rest.controller;

import com.enmivida.rest.exception.UnsuportedMathOperationException;
import com.enmivida.rest.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> doSum(@PathVariable("numberOne") String numberOne,
                                        @PathVariable("numberTwo") String numberTwo) {
        if (!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double sum = Utils.convertToDouble(numberOne) + Utils.convertToDouble(numberTwo);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    @GetMapping("/subtraction/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> doSubtraction(@PathVariable("numberOne") String numberOne,
                                                @PathVariable("numberTwo") String numberTwo) {
        if (!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = Utils.convertToDouble(numberOne) - Utils.convertToDouble(numberTwo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/multiplication/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> doMultipliaction(@PathVariable("numberOne") String numberOne,
                                                @PathVariable("numberTwo") String numberTwo) {
        if (!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = Utils.convertToDouble(numberOne) * Utils.convertToDouble(numberTwo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> doDivision(@PathVariable("numberOne") String numberOne,
                                             @PathVariable("numberTwo") String numberTwo) {
        if (!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = Utils.convertToDouble(numberOne) / Utils.convertToDouble(numberTwo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/average/{numberOne}/{numberTwo}")
    public ResponseEntity<Double> doAverage(@PathVariable("numberOne") String numberOne,
                                            @PathVariable("numberTwo") String numberTwo) {
        if (!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = (Utils.convertToDouble(numberOne) + Utils.convertToDouble(numberTwo)) / 2;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/square/{number}")
    public ResponseEntity<Double> doSquareRoot(@PathVariable("number") String number) {
        if (!Utils.isNumeric(number)) {
            throw new UnsuportedMathOperationException("Please set a numeric value!");
        }
        Double result = Math.sqrt(Utils.convertToDouble(number));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
