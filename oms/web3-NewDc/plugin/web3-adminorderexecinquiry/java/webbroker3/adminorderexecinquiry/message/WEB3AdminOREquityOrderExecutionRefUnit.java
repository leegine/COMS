head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOREquityOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҋ����������Ɖ�Unit (WEB3AdminOREquityOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/08/30 �����F(���u) ���f�� 057
Revision History : 2006/11/20 ����(���u) ���f�� 081
Revision History : 2006/12/19 �����F (���u) �d�l�ύX�E���f��087
Revision History : 2007/06/05 �đo�g (���u) �d�l�ύX�E���f��099
Revision History : 2008/01/23 �g�E�N�| (���u) �d�l�ύX�E���f��112
*/
package webbroker3.adminorderexecinquiry.message;

/**
 * (�Ǘ��Ҋ����������Ɖ�Unit)<BR>
 * <BR>
 * �Ǘ��Ҋ����������Ɖ�Unit�N���X<BR>
 * <BR>
 * WEB3AdminOREquityOrderExecutionRefUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOREquityOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon
{
    /**
     * (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * (������)<BR>
     */
    public String productName;

    /**
     * (�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * (�����敪)<BR>
     * <BR>
     * 0�F��ʁ@@1�F����@@5�F�X�g�b�N�I�v�V����<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (�ٍϋ敪)<BR>
     * <BR>
     * �ٍϋ敪<BR>
     * <BR>
     * 1�F�@@���x�M�p<BR>
     * 2�F�@@��ʐM�p <BR>
     * <BR>
     * ���M�p�����̏ꍇ�Z�b�g����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * repaymentDiv<BR>
     * <BR>
     * 1: Def.REPAYMENT_DIV_MARGIN_SYS<BR>
     * 2: Def.REPAYMENT_DIV_MARGIN_GEN<BR>
     * ��set if marginOrder<BR>
     * <BR>
     */
    public String repaymentDiv = null;

    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l<BR>
     * 7:���s�c�����<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * priceCondType<BR>
     * 0: Def.DEFAULT�@@1: Def.PRESENT_VALUE_LIMIT_PRICE_ORDER�@@<BR>
     * 3: Def.PRIORITY_LIMIT_PRICE_ORDER<BR>�@@5: Def.PARTIALLY_LIMIT_PRICE_ORDER<BR>
     * 7: Def.PARTIALLY_CANCEL_ORDER<BR>
     * <BR>
     */
    public String priceCondType;
    
    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     * <BR>
     */
    public String wlimitExecCondType = null;
    
    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L���@@2�F�X�g�b�v����������<BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     * <BR>
     */
    public String wlimitEnableStatusDiv = null;

    /**
     * (�������ϗ��R)<BR>
     * �������ϗ��R<BR>
     * <BR>
     * 0�F�@@���ϊ�������<BR>
     * 1�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j<BR>
     * 2�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j<BR>
     * 3�F�@@�ۏ؋��ێ������i��ԁj<BR>
     * 9�F�@@�蓮��������<BR>
     * <BR>
     * ���������ϒ����łȂ��ꍇ��null���Z�b�g�����B<BR>
     */
    public String forcedSettleReason;

    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * 0�F�@@�I�[�v��<BR>
     * 1�F�@@����������<BR>
     */
    public String forcedLapseDiv;

    /**
     * (�o�����͉\�t���O)<BR>
     * �o�����͉\�t���O<BR>
     * <BR>
     * true�F�o�����͉\�@@�@@false�F�o�����͕s��
     */
    public boolean inputExecFlag;

    /**
     * (�o������\�t���O)<BR>
     * �o������\�t���O<BR>
     * <BR>
     * true�F�o������\�@@�@@false�F�o������s��
     */
    public boolean cancelExecFlag;

    /**
     * (�Ǘ��Ҋ����������Ɖ�Unit)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * WEB3AdminOREquityOrderExecutionRefUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefUnit
     * @@roseuid 41B40F2303A4
     */
    public WEB3AdminOREquityOrderExecutionRefUnit()
    {

    }
}
@
