# Nikobizz Android Api


### Usage

```java

        private String USERNAME = "";
        private String PASSWORD = "";
        String API_KEY = "2013";
        String Username = "";
        String Password = "";

        Nikobizz nikobizz = new Nikobizz(API_KEY,Username,Password);

        nikobizz.sendMPESA("+254717103598", "10", "M-PESA", "", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //on network failure
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseString = response.body().string();
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response code " + response);
                }

                Log.i(TAG, responseString);
            }
        });
        
        //or
        
        
        nikobizz.sendMPESA("+254701653740", "1000", "M-PESA", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //on network failure
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseString = response.body().string();
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response code " + response);
                }

                Log.i(TAG, responseString);
            }
        });



```
