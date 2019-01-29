head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�������̓��X�|���X(WEB3EquityOrderBuyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/19 �R�w�� (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �c�Č��Ή��@@�d�l�ύX�Ǘ��䒠�@@No.385
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1085
*/

package webbroker3.equity.message;

/**
 * �i�����������t�������̓��X�|���X�j�B<BR>
 * <BR>
 * �����������t�������͉����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityBuyInputResponse extends WEB3EquityInputCommonResponse
{

    /**
     * (���t�\���z) <BR>
     * �����������t�]��<BR>
     */
    public String tradingPower;

    /**
     * ������ <BR>
     * ���ڎw��̏ꍇ�ɕK�v <BR>
     */
    public String productName;

    /**
     * �s��R�[�h�ꗗ <BR>
     * �I���\�s��ꗗ <BR>
     */
    public String[] marketList;

    /**
     * �����敪�ꗗ <BR>
     * 0�F��ʁ@@�@@1�F���� <BR>
     */
    public String[] taxTypeList;

    /**
     * ���������敪�ꗗ <BR>
     * 1�F��������@@2�F�o����܂Œ��� <BR>
     */
    public String[] expirationDateTypeList;

    /**
     * ���������敪�ꗗ <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l <BR>
     */
    public String[] orderCondTypeList;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;

    /**
     * (�������i)<BR>
     * �������i<BR>
     */
    //public String offFloorOrderPrice;
    

    /**
     * �|�������t�B�b�N�^�C�v�B<BR>
     */
    public static final String PTYPE = "equity_order_buy_input";

    /**
     * �V���A���o�[�W����UID <BR>
     */
    public static final long serialVersionUId = 200405081330L;

    /**
     * @@roseuid 409B37000261
     */
    public WEB3EquityBuyInputResponse()
    {

    }
    /**
     * @@roseuid 409B37000261
     */
    public WEB3EquityBuyInputResponse(WEB3EquityBuyInputRequest l_request)
    {
        super(l_request);
    }
}
@
