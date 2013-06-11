package pack.bi.utils;

public interface ResultsListener {
    public void onResultsSucceeded();

    public void onError(String message);
}
