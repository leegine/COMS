head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������Ɖ�X�|���X(WEB3MarginContractReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p��������Ɖ�X�|���X�j�B<br>
 * <br>
 * �M�p��������Ɖ�X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginContractReferenceResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_contractReference";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101802L;      
    /**
     * (�����ꗗ)<BR>
     * �M�p��������R�[�h���̂̈ꗗ
     */
    public WEB3MarginProductCodeNameUnit[] productCodeNames;
    
    /**
     * (�s��R�[�h�̈ꗗ)
     */
    public String[] marketList;
    
    /**
     * (���y�[�W��)
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�����Ɖ�׈ꗗ)<BR>
     * �M�p��������Ɖ�ׂ̈ꗗ
     */
    public WEB3MarginContractReferenceUnit[] contractReferenceUnits;
    
    /**
     * @@roseuid 41403F6F02F8
     */
    public WEB3MarginContractReferenceResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginContractReferenceResponse(WEB3MarginContractReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
