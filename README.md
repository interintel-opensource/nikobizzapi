# Nikobizz Android Api


### Quick Setup


- Add the JitPack repository to your build file 
Add it in your `build.gradle` at the end of repositories:
```

repositories {
    // ...
    maven { url "https://jitpack.io" }
}

```
        
- Add nikobizzapi dependency
```

dependencies {
        //...
    compile 'com.github.interintel-opensource:nikobizzapi:v1.0.1'
}
```
- Add OkHTTP dependency (is a requirement for passing a callback to the service call)
```

dependencies {
        //...
    compile 'com.squareup.okhttp3:okhttp:3.5.0'
}

 

```


### Usage

```java

        String USERNAME = "";
        String PASSWORD = "";
        String API_KEY = "20177";
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
