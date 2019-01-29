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
filename	WEB3EquitySellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�������͉����@@���X�|���X�f�[�^�N���X(WEB3EquitySellInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                 : 2006/08/29 �����F(���u) ���f�� 972
*/

package webbroker3.equity.message;

/**
 * �i�����������t�������̓��X�|���X�j�B<BR>
 * <BR>
 * �����������t�������͉����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquitySellInputResponse extends WEB3EquityInputCommonResponse
{

    /**
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * ������<BR>
     */
    public String productName;

    /**
     * �I���\�s��ꗗ<BR>
     */
    public String[] marketList;

    /**
     * �����敪<BR>
     * 0�F��ʁ@@�@@1�F����@@�@@5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;

    /**
     * ��������<BR>
     */
    public String orderQuantity;

    /**
     * �T�Z�뉿�P��<BR>
     */
    public String estimatedBookPrice;

    /**
     * ���������敪�ꗗ<BR>
     * 1�F��������@@2�F�o����܂Œ���<BR>
     */
    public String[] expirationDateTypeList;

    /**
     * ���������敪�ꗗ<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String[] orderCondTypeList;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_order_sellinput";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200404301200L;

    /**
     * @@roseuid 4091F8C1033E
     */
    public WEB3EquitySellInputResponse()
    {

    }

    /**
      * �R���X�g���N�^�B<BR>
      * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
      *<BR>
      * @@param l_request ���N�G�X�g�I�u�W�F�N�g
      */
    public WEB3EquitySellInputResponse(WEB3EquitySellInputRequest l_request)
    {
        super(l_request);
    }
}
@
