head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityDeliveryChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �o�ɕϓ�(WEB3TPSecurityDeliveryChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 �R�c�@@��i (FLJ) �V�K�쐬
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�o�ɕϓ�)
 */
public class WEB3TPSecurityDeliveryChange
    extends WEB3TPSecurityChange
{

    /**
     * @@roseuid 41087DA301D7
     */
    public WEB3TPSecurityDeliveryChange()
    {

    }

    /**
     * (create�o�ɕϓ�)<BR>
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityDeliveryChange
     * @@roseuid 40D7F483027E
     */
    public static WEB3TPSecurityDeliveryChange create()
    {
        return new WEB3TPSecurityDeliveryChange();
    }

    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ����f�����v�Z����B<BR>
     * <BR>
     * �ϓ����f�J�n��=�c�Ɠ�(T+0)<BR>
     * �ϓ����f�I����=�c�Ɠ�(T+5)
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 40B55562022C
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        setReflectStartDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0));
        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));

        setDeliveryDate(l_datDeliveryDate);    
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ�
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .toString();
    }

}
@
