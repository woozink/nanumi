import React, {useEffect} from 'react';
import {RecoilRoot} from 'recoil';
import {QueryClient, QueryClientProvider} from '@tanstack/react-query';
import StackNavigator from './navigator/StackNavigator';
import AsyncStorage from '@react-native-async-storage/async-storage';
import * as encoding from 'text-encoding';

const queryClient = new QueryClient();
const App = () => {
  // useEffect(() => {
  //   const isUser = async () => {
  //     const asyncUser = await AsyncStorage.getItem('user');
  //     if (!asyncUser) navigation.navigate('Login');
  //   };
  //   isUser();
  // }, []);

  return (
    <RecoilRoot>
      <QueryClientProvider client={queryClient}>
        <StackNavigator />
      </QueryClientProvider>
    </RecoilRoot>
  );
};

export default App;
