contract Notifier {
    int truth;
    address addressToNotify;

    constructor(int lie, address _addressToNotify){
        truth = lie;
        addressToNotify = _addressToNotify;
    }

    checkMessage() returns int {
        while (truth > 42) {
            truth = truth - 2;
            if (truth == 42) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    transact() returns bool {
        try
            sender.checkMessage();
        abort {
            return false;
        } success {
            return true;
        }
    }
}