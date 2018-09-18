<?php

    require_once('app/Mage.php'); //Path to Magento
    umask(0);
    Mage::app();
    
    $status = $_POST['status'];
    #$status = 'complete';
    #$email = "asad@trenzasoft.com";
    $orders = Mage::getModel('sales/order')->getCollection()
    ->addFieldToFilter('status', $status)
    ->addAttributeToSelect('customer_email');
    //->addAttributeToSelect('');
    foreach ($orders as $order) {
        $email = $order->getCustomerEmail();
        $arr['email'] = $email;
        $emparray[] = $arr;
    
    }
    //print_r($emparray);
    echo json_encode($emparray);