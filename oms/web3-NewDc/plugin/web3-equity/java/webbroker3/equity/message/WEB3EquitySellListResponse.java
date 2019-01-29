head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�ꗗ���X�|���X(WEB3EquitySellListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �C�ї� (���u) �V�K�쐬
Revesion History : 2004/12/07 �����a��(SRA) �c�Č��Ή� �m��.�O�S�U
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����������t�ꗗ���X�|���X�j�B<BR>
 * <BR>
 * ���������ۗL���Y�ꗗ�Ɖ���@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquitySellListResponse extends WEB3GenResponse
{

    /**
     * �����ꗗ<BR>
     * �i�R�[�h�E���̂��܂ށj�����ꗗ<BR>
     */
    public WEB3EquityProductCodeNameUnit[] productCodeNames;

    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�̔z��
     */
    public String[] marketList;

    /**
     * ����<BR>
     * ���������ۗL���Y�ꗗ�Ɖ�ۗL���Y�I�u�W�F�N�g�̔z��<BR>
     */
    public WEB3EquityAssetUnit[] equityAssetUnits;

    /**
     * ���y�[�W��<BR>
     * �Y������S�y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �Y������S�f�[�^��<BR>
     */
    public String totalRecords;

    /**
     * �\���y�[�W�ԍ�
     * �i�ʏ�́A�v���y�[�W�ԍ������̂܂܃Z�b�g�����j
     */
    public String pageIndex;

    /**
     * ����I���x���s��R�[�h�ꗗ<BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405101030L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_sell_list";

    /**
     * @@roseuid 409F5EA30110
     */
    public WEB3EquitySellListResponse()
    {

    }
    /**
     * �R���X�g���N�^�B<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@paramWEB3EquityAssetInquiryRequest l_request
     */
    public WEB3EquitySellListResponse(WEB3EquitySellListRequest l_request)
    {
        super(l_request);
    }
}
@
