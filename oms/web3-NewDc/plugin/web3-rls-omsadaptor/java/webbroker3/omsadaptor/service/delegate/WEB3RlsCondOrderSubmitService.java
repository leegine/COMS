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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RlsCondOrderSubmitService�C���^�[�t�F�[�X(WEB3RlsCondOrderSubmitService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 ��(FLJ) �V�K�쐬
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
 * @@author �� (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsCondOrderSubmitService
    extends Service
{

    /**
     * �i���[���G���W������ꌏ�����ʒm�j<br />
     * <br />
     * ���[���G���W������ꌏ�����ʒm�B�@@<br />
     * <br />
     * @@param l_notifyParams - ���[���G���W������ꌏ�����ʒm<br />
     * <br />
     */
    public String submitRlsCondOrder(RlsConOrderHitNotifyParams l_notifyParams) throws
        WEB3SystemLayerException,
        WEB3BaseException;

    /**
     * �i���[���G���W������ꌏ�����ʒm�j<br />
     * <br />
     * ���[���G���W������ꌏ�����ʒm�B�@@<br />
     * <br />
     * @@param l_subaccount - �⏕����<br />
     * @@param l_intOrderType - �����t�����^�C�v
     * @@param l_lngConOrderId - ����ID<br />
     * @@param l_productType - ���i�^�C�v<br />
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
