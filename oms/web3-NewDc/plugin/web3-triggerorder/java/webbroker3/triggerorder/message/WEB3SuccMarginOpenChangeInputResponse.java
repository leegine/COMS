head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������V�K�����̓��N�G�X�g�N���X(WEB3SuccMarginOpenChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputResponse;

/**
 * (�i�A���j�M�p��������V�K�����̓��X�|���X�N���X)<BR>
 * �i�A���j�M�p��������V�K�����̓��X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3SuccMarginOpenChangeInputResponse extends WEB3MarginOpenMarginChangeInputResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenChangeInput";
    
    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;
    
    /**
     * (���������敪�ꗗ)<BR>
     * ���������敪�ꗗ�B<BR>
     * �i1�F��������@@2�F�o����܂Œ����j<BR>
     */
    public String[] expirationDateTypeList;
        
    /**
     * @@roseuid 4369CBED000F
     */
    public WEB3SuccMarginOpenChangeInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccMarginOpenChangeInputResponse(WEB3SuccMarginOpenChangeInputRequest l_request)
    {
        super(l_request);
    }     
}
@
