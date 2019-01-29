head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginContractListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ό����ꗗ���X�|���X(WEB3MarginCloseMarginContractListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p������ό����ꗗ���X�|���X�j�B<br>
 * <br>
 * �M�p������ό����ꗗ���X�|���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginContractListResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginContractList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101648L;         
    /**
     * (�����R�[�h)
     */
    public String productCode;
    
    /**
     * (������)
     */
    public String productName;
    
    /**
     * (�s��R�[�h)
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����
     */
    public String taxType;
    
    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����
     */
    public String contractType;
    
    /**
     * (�ٍ�)<BR>
     * 1�F��ʐM�p 2�F���x�M�p
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (���Ϗ����敪)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������
     */
    public String closingOrder;
    
    /**
     * (���ό����ꗗ���׍s)<BR>
     * �M�p������ό������׍s
     */
    public WEB3MarginCloseMarginContractGroup[] closeMarginContractGroups;
    
    /**
     * @@roseuid 414032D002B6
     */
    public WEB3MarginCloseMarginContractListResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginCloseMarginContractListResponse(WEB3MarginCloseMarginContractListRequest l_request)
    {
        super(l_request);
    }
}
@
