package com.schenkel.axel.muzeibasket;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.schenkel.axel.muzeibasket.DataAccess.Implementations.SharedPreferencesService;
import com.schenkel.axel.muzeibasket.NetworkUtils.Connectivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Created by axel on 25/05/17.
 */

public class ConnectivityTest {

    @Mock
    Context context;
    @Mock
    ConnectivityManager connectivityManager;
    @Mock
    NetworkInfo networkInfo;
    @Mock
    Connectivity connectivity;

    @Before
    public void before() throws Exception {

        InitMocks();

        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        when(networkInfo.isConnected()).thenReturn(true);

    }

    private void InitMocks() {
        this.connectivity =  Mockito.spy(Connectivity.class);
        this.context = Mockito.mock(Context.class);
        this.connectivityManager = Mockito.mock(ConnectivityManager.class);
        this.networkInfo = Mockito.mock(NetworkInfo.class);


    }

    @Test
    public void AirplaneModeOnTest(){
        doReturn(true).when(connectivity).isAirplaneModeOn(context);
        assertFalse(connectivity.isConnected(context));
    }

    @Test
    public void EdgeConnectivityTest(){
        doReturn(false).when(connectivity).isAirplaneModeOn(context);
        doReturn(ConnectivityManager.TYPE_MOBILE).when(networkInfo).getType();
        doReturn(TelephonyManager.NETWORK_TYPE_EDGE).when(networkInfo).getSubtype();
        assertFalse(connectivity.isConnected(context));
    }

    @Test
    public void WiFiConnectivityTest(){
        doReturn(false).when(connectivity).isAirplaneModeOn(context);
        doReturn(ConnectivityManager.TYPE_WIFI).when(networkInfo).getType();
        doReturn(ConnectivityManager.TYPE_WIFI).when(networkInfo).getSubtype();
        assertTrue(connectivity.isConnected(context));
    }


    @Test
    public void LTEConnectivityTest(){
        doReturn(false).when(connectivity).isAirplaneModeOn(context);
        doReturn(ConnectivityManager.TYPE_MOBILE).when(networkInfo).getType();
        doReturn(TelephonyManager.NETWORK_TYPE_LTE).when(networkInfo).getSubtype();
        assertTrue(connectivity.isConnected(context));
    }
}
