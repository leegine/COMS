head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderSubmitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RlsCondOrderSubmitServiceインターフェース(WEB3RlsCondOrderSubmitService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 劉(FLJ) 新規作成
 */
package webbroker3.omsadaptor.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.gentrade.*;
import webbroker3.rlsgateway.data.*;

/**
 *
 *
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsCondOrderSubmitService
    extends Service
{

    /**
     * （ルールエンジンから一件処理通知）<br />
     * <br />
     * ルールエンジンから一件処理通知。　@<br />
     * <br />
     * @@param l_notifyParams - ルールエンジンから一件処理通知<br />
     * <br />
     */
    public String submitRlsCondOrder(RlsConOrderHitNotifyParams l_notifyParams) throws
        WEB3SystemLayerException,
        WEB3BaseException;

    /**
     * （ルールエンジンから一件処理通知）<br />
     * <br />
     * ルールエンジンから一件処理通知。　@<br />
     * <br />
     * @@param l_subaccount - 補助口座<br />
     * @@param l_intOrderType - 条件付注文タイプ
     * @@param l_lngConOrderId - 注文ID<br />
     * @@param l_productType - 商品タイプ<br />
     * <br />
     */
    public void submitRlsCondOrder(WEB3GentradeSubAccount l_subaccount,
                                   int l_intOrderType,
                                   long l_lngConOrderId,
                                   ProductTypeEnum l_productType
                                   ) throws WEB3SystemLayerException,
        WEB3BaseException;

}
@
