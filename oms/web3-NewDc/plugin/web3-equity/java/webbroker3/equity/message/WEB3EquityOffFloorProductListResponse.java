head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOffFloorProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����O���������ꗗ���X�|���X(WEB3EquityOffFloorProductListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �����a��(SRA) �V�K�쐬
                   2004/12/29 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i����O���������ꗗ���X�|���X�j�B<BR>
 * <BR>
 * ����O���������ꗗ�����@@���X�|���X�f�[�^�N���X
 * @@author �����a��(SRA) 
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListResponse extends WEB3GenResponse
{

    /**
     * �iserialVersionUID�j�B
     */
    public static final long serialVersionUID = 200412171000L;

    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "equity_off_floor_product_list";
    
    /**
     * �i�����ꗗ�j�B<BR>
     * <BR>
     * ����O���������ꗗ
     */
    public WEB3EquityOffFloorProductGroup[] productList;
    
    /**
     * �i����I���x���s��R�[�h�ꗗ�j�B<BR>
     * <BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ
     */
    public String[] messageSuspension;
    
    /**
     * �i�R���X�g���N�^�j�B<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@paramWEB3EquityAssetInquiryRequest l_request
     */
    public WEB3EquityOffFloorProductListResponse(WEB3EquityOffFloorProductListRequest l_request)
    {
        super(l_request);
    }
}
@
