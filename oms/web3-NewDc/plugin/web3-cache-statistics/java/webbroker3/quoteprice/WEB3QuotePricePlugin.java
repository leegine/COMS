head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.52.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePricePlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuotePricePlugin�v���O�C���̃v���O�C���N���X(WEB3QuotePricePlugin.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/08/18 �� (FLJ)�V�K�쐬
 */
package webbroker3.quoteprice;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.quoteprice.handler.*;
import webbroker3.quoteprice.message.*;
import webbroker3.quoteprice.service.delegate.*;
import webbroker3.quoteprice.service.delegate.stdimpls.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3QuotePricePlugin�v���O�C���̃v���O�C���N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3QuotePricePlugin
    extends Plugin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePricePlugin.class);

    private static boolean isPlugged = false;

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3QuotePricePlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3QuotePricePlugin.class);
    }

    /**
     * ���̃v���O�C���N���X�̃��C�����\�b�h
     */
    public static void onPlug() throws Exception
    {

        KernelPlugin.plug();

        regClass(WEB3QuotePriceRequest.class);
        regClass(WEB3QuotePriceResponse.class);

        regHandler(
            WEB3QuotePricePlugin.class,
            WEB3QuotePriceRequest.class,
            WEB3QuotePriceHandler.class,
            "handleWEB3QuotePriceRequest");

        Services.registerService(
            WEB3QuotePriceMultiInstitutionService.class,
            new WEB3QuotePriceMultiInstitutionServiceImpl());

        Services.registerService(
            WEB3QuotePriceEquityService.class,
            new WEB3QuotePriceEquityServiceImpl());

        Services.registerService(
                WEB3QuotePriceIfoService.class,
                new WEB3QuotePriceIfoServiceImpl());

        isPlugged = true;

        log.info("WEB3QuotePricePlugin was plugged.");
    }

    public static boolean isPlugged()
    {
        return isPlugged;
    }

}
@
