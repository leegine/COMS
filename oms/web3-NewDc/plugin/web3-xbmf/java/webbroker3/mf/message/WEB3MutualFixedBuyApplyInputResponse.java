head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�V�K�\�����̓��X�|���X (WEB3MutualFixedBuyApplyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 *�i���M�莞��z���t�V�K�\�����̓��X�|���X�j<BR>
 * ���M�莞��z���t�V�K�\�����̓��X�|���X<BR>
 * 
 * @@author ���G�� (���u) 
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyApplyInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_apply_input";
    
    /**
     * SerialVersionUID<BR>
     */ 
    public final static long serialVersionUID = 200606261701L;
  
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */   
    public String accountNameKana;

    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */   
    public String accountName;
  
    /**
     * (�X�֔ԍ�)<BR>
     * �X�֔ԍ�<BR>
     */   
    public String zipCode;
 
    /**
     * (�Z���P)<BR>
     * �Z���P<BR>
     */   
    public String address1;
 
    /**
     * (�Z���Q)<BR>
     * �Z���Q<BR>
     */   
    public String address2;
 
    /**
     * (�Z���R)<BR>
     * �Z���R<BR>
     */   
    public String address3;
   
    /**
     * (���M�莞��z���t�����ꗗ)<BR>
     * ���M�莞��z���t�����ꗗ<BR>
     */   
    public WEB3MutualFixedBuyConditionUnit[] conditionList;
  
    /**
     * (���M�����J�e�S���[�ꗗ)<BR>
     * ���M�����J�e�S���[�ꗗ<BR>
     */   
    public WEB3MutualProductCategoryUnit[] categoryList;
 
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MutualFixedBuyApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
   
    /**
     * (���M�莞��z���t�V�K�\�����̓��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyApplyInputResponse() 
    {     
    }    
}
@
