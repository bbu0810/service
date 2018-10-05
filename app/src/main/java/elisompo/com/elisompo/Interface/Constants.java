package elisompo.com.elisompo.Interface;

import android.Manifest;

public interface Constants {

    // +++++++++++ URL +++++++++++++ //

    String LOGO_NAME = "Elisompo";
    int REQUEST_EXTERNAL_STORAGE = 1;
    String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    String TEST_PHONENUMBER = "79160577540";
    String TEST_TOKEN = "jytb7g8vi8i";

    // +++++++++++ URL +++++++++++++ //

    String BASIC_URL = "http://wabot.wnets.net/get_message.php?token=jytb7g8vi8i";
//    String REPORT_URL = "http://wabot.wnets.net/report_job.php";
    String REPORT_URL = "http://wabot.wnets.net/get_message.php?token=jytb7g8vi8i";
    // +++++++++++ PARAMETERS +++++++++++++ //

    String PHONE = "phone";
    String TEXT = "text";
    String IMAGE = "image";
    String JOB_ID = "job_id";
    String SLEEP = "sleep";

    // +++++++++++ RESPHONES +++++++++++++ //

    String MESSAGE = "message";
    String RESPHONE = "resphone";
    String SUCCESS = "success";

    // +++++++++++ RESPHONES +++++++++++++ //

    String ERROR = "error";
    int ERROR_204 = 204;
    int ERROR_400 = 400;
    int ERROR_404 = 404;
    int ERROR_405 = 405;
    int ERROR_406 = 406;
    int ERROR_500 = 500;
    int ERROR_505 = 505;

    // +++++++++++ RESPHONES +++++++++++++ //

    String NOT_INSTALLED = "Whatsapp have not been installed.";

}
