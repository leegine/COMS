head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�����ꗗ���X�|���X(WEB3BondApplyBuyProductListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (������/���t�����ꗗ���X�|���X)<BR>
 * ������/���t�����ꗗ���X�|���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondApplyBuyProductListResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyProductList";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;  
    
    /**
     * (�ʉݏ��ꗗ)<BR>
     * �ʉݏ��ꗗ<BR>
     */
    public WEB3BondCurrencyInfo[] currencyList;
    
    /**
     * (����/���t�����ꗗ)<BR>
     * ����/���t�����ꗗ<BR>
     */
    public WEB3BondApplyBuyProductInfo[] productList;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (������/���t�����ꗗ���X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44B6E4AB01A5
     */
    public WEB3BondApplyBuyProductListResponse() 
    {
     
    }
    
     /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondApplyBuyProductListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
