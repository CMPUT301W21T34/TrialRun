package com.example.trialsinthewild;

public class QRScanHelper {
    /*
        US 03.01.01
        As an experimenter, I want to be able to generate QR codes that I can print for a specific experiment and trial result (for instance PASS for a binomial trial I subscribed to).
        US 03.02.01
        As an experimenter, I want to be able scan QR codes to indicate success or failure, or increment counts for trials I subscribed to.
        US 03.03.01
        As an experimenter, I want to be able to register an arbitrary bar code (such as one off of your favourite book) to act a specific experiment result for a trial.

        Generate QR codes for a specific experiment+outcome

        Counts: No failure, you have seen something one time
                Only one QR code to increase count by 1

        Binomial Trial: Only success or failure, each trial is 1 success or 1 failure
                        At least 2 QR codes - 1 for success, 1 for failure

        Non-negative integer counts: A trial is 0 or more. 0 is failure
                                     QR codes to indicate the integer count
                                     eg. QR codes for every count from 0-possibilities

        Measurements: No QR codes can really be used.

        Was shown an example of how QR codes work using this site: https://www.qr-code-generator.com/

        Prof said "You're probably going to use a library like zxing for barcodes and QR Codes."
        **I found this, if you end up using it please cite it
        https://www.geeksforgeeks.org/how-to-generate-and-read-qr-code-with-java-using-zxing-library/

        *Examples*
        ExperimentID:Success
        ExperimentID:Failure
        ExperimentID:47
        ExperimentID:++ (maybe indicates increasing a counter by 1)

        Scan QR codes which indicates a trial outcome
            - Anyone can generate a QR code when needed, doesn't have to be approved/done by the owner.
            - When scanning Barcode, you're just associating a specific barcode with what you would make a QR code for.


        Be able to register a BARCODE to act in place of a QR code for a trial outcome
            - Users who register a barcode to scan for a specific trial only applies to that user. Only needs to be stored locally for that user.
            - Barcode registration can be removed.
            - More than one barcode can be associated with one result.
     */
}
