package ir.elegam.school.Interface;

/**
 * Created by Droid on 8/9/2016.
 */
public interface IWebservice {
    void getResult(Object result) throws Exception;
    void getError(String ErrorCodeTitle)throws Exception;
}
