head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerSearchListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPAdvanceCustomerSearchListResponse.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/02/08 asano(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �ۏ؋��ێ�������/���֋������ڋq�����ꗗ��ʕ\�����X�|���X
 */
public class WEB3AdminTPAdvanceCustomerSearchListResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admintradingpower_advance_customer_search_list";
       
    /**
     * (�l�􂢏I���敪)
     */
    public String[] markToMarketEndDiv;
    
    /**
     * (���֋��ڋq�ꗗ)
     */
    public WEB3AdminTPAdvanceCustomerUnit[] adminAdvanceCustomerUnits;
    
    /**
     * (���y�[�W��)
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)
     */
    public String pageIndex;
        
    /**
     * (�R���X�g���N�^)
     */
    public WEB3AdminTPAdvanceCustomerSearchListResponse()
    {
    }

}
@
