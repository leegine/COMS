head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPAdvanceCustomerDetailResponse.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �ۏ؋��ێ�������/���֋������ڋq�ڍ׉�ʕ\�����X�|���X
 */
public class WEB3AdminTPAdvanceCustomerDetailResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admintradingpower_advance_customer_detail";

    /**
     * (���֋����׈ꗗ)
     */
    public WEB3AdminTPAdvanceDetailUnit[] advanceCustomerDetailUnits;
        
    /**
     * �R���X�g���N�^
     */
    public WEB3AdminTPAdvanceCustomerDetailResponse()
    {
    }

}
@
