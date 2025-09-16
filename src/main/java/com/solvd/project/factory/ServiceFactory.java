package com.solvd.project.factory;

import com.solvd.project.dao.*;
import com.solvd.project.service.*;
import com.solvd.project.service.mybatisiml.*;

public class ServiceFactory {
    public static PolicyHolderService createPolicyHolderService() {
        return new PolicyHolderService(new PolicyHolderDAO());
    }

    public static PolicyService createPolicyService() {
        return new PolicyService(new PolicyDAO());
    }

    public static VehicleService createVehicleService() {
        return new VehicleService(new VehicleDAO());
    }

    public static DriverService createDriverService() {
        return new DriverService(new DriverDAO());
    }

    public static PaymentsService createPaymentsService() {
        return new PaymentsService(new PaymentDAO());
    }

    // MyBatis versions
    public static PolicyHolderServiceMyBatis createPolicyHolderServiceMyBatis() {
        return new PolicyHolderServiceMyBatis(new PolicyHolderDAO());
    }

    public static PolicyServiceMyBatis createPolicyServiceMyBatis() {
        return new PolicyServiceMyBatis(new PolicyDAO());
    }

    public static VehicleServiceMyBatis createVehicleServiceMyBatis() {
        return new VehicleServiceMyBatis(new VehicleDAO());
    }

    public static DriverServiceMyBatis createDriverServiceMyBatis() {
        return new DriverServiceMyBatis(new DriverDAO());
    }

    public static PaymentsServiceMyBatis createPaymentsServiceMyBatis() {
        return new PaymentsServiceMyBatis(new PaymentDAO());
    }
}
