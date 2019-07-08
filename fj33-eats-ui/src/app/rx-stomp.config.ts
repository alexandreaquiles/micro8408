import { InjectableRxStompConfig } from '@stomp/ng2-stompjs';

import { environment } from 'src/environments/environment';
import SockJS from 'sockjs-client';

export const rxStompConfig: InjectableRxStompConfig = {
  webSocketFactory: () => {
    return new SockJS(environment.baseUrl + '/socket');
  }
};
