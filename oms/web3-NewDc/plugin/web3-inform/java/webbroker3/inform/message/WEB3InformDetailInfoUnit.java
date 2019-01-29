head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �e��A�����N���X(WEB3InformDetailInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/20 ������(���u) �쐬
Revesion History    : 2007/05/11 ������ (���u) ���f��No.35
Revesion History    : 2007/05/14 ������ (���u) ���f��No.42
Revesion History    : 2007/06/18 ���n�m(���u) �C�� ���f��088�A091
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�e��A�����)<BR>
 * �e��A�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3InformDetailInfoUnit extends Message
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformDetailInfoUnit.class);
            
    /**
     * (�A�����)<BR>
     * �A�����<BR>
     */
    public String informType;
    
    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�����ԍ�)<BR>
     * �����ԍ�<BR>
     */
    public String accountNumber;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String accountName;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;
    
    /**
     * (�敪�P)<BR>
     * �敪�P<BR>
     */
    public String div1;
    
    /**
     * (�敪�Q)<BR>
     * �敪�Q
     */
    public String div2;
    
    /**
     * (�敪�R)<BR>
     * �敪�R
     */
    public String div3;
    
    /**
     * (�敪�S)<BR>
     * �敪�S
     */
    public String div4;
    
    /**
     * (�敪�T)<BR>
     * �敪�T
     */
    public String div5;
    
    /**
     * (�敪�U)<BR>
     * �敪�U
     */
    public String div6;
    
    /**
     * (�敪�V)<BR>
     * �敪�V
     */
    public String div7;
    
    /**
     * (�敪�W)<BR>
     * �敪�W
     */
    public String div8;
    
    /**
     * (�敪�X)<BR>
     * �敪�X
     */
    public String div9;
    
    /**
     * (�敪�P�O)<BR>
     * �敪�P�O
     */
    public String div10;
    
    /**
     * (�敪�P�P)<BR>
     * �敪�P�P
     */
    public String div11;
    
    /**
     * (�敪�P�Q)<BR>
     * �敪�P�Q
     */
    public String div12;
    
    /**
     * (�敪�P�R)<BR>
     * �敪�P�R
     */
    public String div13;
    
    /**
     * (�敪�P�S)<BR>
     * �敪�P�S
     */
    public String div14;
    
    /**
     * (�敪�P�T)<BR>
     * �敪�P�T
     */
    public String div15;
    
    /**
     * (�敪�P�U)<BR>
     * �敪�P�U
     */
    public String div16;
    
    /**
     * (�敪�P�V)<BR>
     * �敪�P�V
     */
    public String div17;
    
    /**
     * (�敪�P�W)<BR>
     * �敪�P�W
     */
    public String div18;
    
    /**
     * (�敪�P�X)<BR>
     * �敪�P�X
     */
    public String div19;
    
    /**
     * (�敪�Q�O)<BR>
     * �敪�Q�O
     */
    public String div20;
    
    /**
     * (�敪�Q�P)<BR>
     * �敪�Q�P
     */
    public String div21;
    
    /**
     * (�敪�Q�Q)<BR>
     * �敪�Q�Q
     */
    public String div22;
    
    /**
     * (�敪�Q�R)<BR>
     * �敪�Q�R
     */
    public String div23;
    
    /**
     * (�敪�Q�S)<BR>
     * �敪�Q�S
     */
    public String div24;
    
    /**
     * (�敪�Q�T)<BR>
     * �敪�Q�T
     */
    public String div25;
    
    /**
     * (�敪�Q�U)<BR>
     * �敪�Q�U
     */
    public String div26;
    
    /**
     * (�敪�Q�V)<BR>
     * �敪�Q�V
     */
    public String div27;
    
    /**
     * (�敪�Q�W)<BR>
     * �敪�Q�W
     */
    public String div28;
    
    /**
     * (�敪�Q�X)<BR>
     * �敪�Q�X
     */
    public String div29;
    
    /**
     * (�敪�R�O)<BR>
     * �敪�R�O
     */
    public String div30;
    
    /**
     * (�敪�R�P)<BR>
     * �敪�R�P
     */
    public String div31;
    
    /**
     * (�敪�R�Q)<BR>
     * �敪�R�Q
     */
    public String div32;
    
    /**
     * (�敪�R�R)<BR>
     * �敪�R�R
     */
    public String div33;
    
    /**
     * (�敪�R�S)<BR>
     * �敪�R�S
     */
    public String div34;
    
    /**
     * (�敪�R�T)<BR>
     * �敪�R�T
     */
    public String div35;
    
    /**
     * (�敪�R�U)<BR>
     * �敪�R�U
     */
    public String div36;
    
    /**
     * (�敪�R�V)<BR>
     * �敪�R�V
     */
    public String div37;
    
    /**
     * (�敪�R�W)<BR>
     * �敪�R�W
     */
    public String div38;
    
    /**
     * (�敪�R�X)<BR>
     * �敪�R�X
     */
    public String div39;
    
    /**
     * (�敪�S�O)<BR>
     * �敪�S�O
     */
    public String div40;
    
    /**
     * (�R�[�h�P)<BR>
     * �R�[�h�P
     */
    public String code1;
    
    /**
     * (�R�[�h�Q)<BR>
     * �R�[�h�Q
     */
    public String code2;
    
    /**
     * (�R�[�h�R)<BR>
     * �R�[�h�R
     */
    public String code3;
    
    /**
     * (�R�[�h�S)<BR>
     * �R�[�h�S
     */
    public String code4;
    
    /**
     * (�R�[�h�T)<BR>
     * �R�[�h�T
     */
    public String code5;
    
    /**
     * (�R�[�h�U)<BR>
     * �R�[�h�U
     */
    public String code6;
    
    /**
     * (�R�[�h�V)<BR>
     * �R�[�h�V
     */
    public String code7;
    
    /**
     * (�R�[�h�W)<BR>
     * �R�[�h�W
     */
    public String code8;
    
    /**
     * (�R�[�h�X)<BR>
     * �R�[�h�X
     */
    public String code9;
    
    /**
     * (�R�[�h�P�O)<BR>
     * �R�[�h�P�O
     */
    public String code10;
    
    /**
     * (�e�L�X�g�P)<BR>
     * �e�L�X�g�P
     */
    public String txt1;
    
    /**
     * (�e�L�X�g�Q)<BR>
     * �e�L�X�g�Q
     */
    public String txt2;
    
    /**
     * (�e�L�X�g�R)<BR>
     * �e�L�X�g�R
     */
    public String txt3;
    
    /**
     * (�e�L�X�g�S)<BR>
     * �e�L�X�g�S
     */
    public String txt4;
    
    /**
     * (�e�L�X�g�T)<BR>
     * �e�L�X�g�T
     */
    public String txt5;
    
    /**
     * (�e�L�X�g�U)<BR>
     * �e�L�X�g�U
     */
    public String txt6;
    
    /**
     * (�e�L�X�g�V)<BR>
     * �e�L�X�g�V
     */
    public String txt7;
    
    /**
     * (�e�L�X�g�W)<BR>
     * �e�L�X�g�W
     */
    public String txt8;
    
    /**
     * (�e�L�X�g�X)<BR>
     * �e�L�X�g�X
     */
    public String txt9;
    
    /**
     * (�e�L�X�g�P�O)<BR>
     * �e�L�X�g�P�O
     */
    public String txt10;
    
    /**
     * (�e�L�X�g�P�P)<BR>
     * �e�L�X�g�P�P
     */
    public String txt11;
    
    /**
     * (�e�L�X�g�P�Q)<BR>
     * �e�L�X�g�P�Q
     */
    public String txt12;
    
    /**
     * (�e�L�X�g�P�R)<BR>
     * �e�L�X�g�P�R
     */
    public String txt13;
    
    /**
     * (�e�L�X�g�P�S)<BR>
     * �e�L�X�g�P�S
     */
    public String txt14;
    
    /**
     * (�e�L�X�g�P�T)<BR>
     * �e�L�X�g�P�T
     */
    public String txt15;
    
    /**
     * (�e�L�X�g�P�U)<BR>
     * �e�L�X�g�P�U
     */
    public String txt16;
    
    /**
     * (�e�L�X�g�P�V)<BR>
     * �e�L�X�g�P�V
     */
    public String txt17;
    
    /**
     * (�e�L�X�g�P�W)<BR>
     * �e�L�X�g�P�W
     */
    public String txt18;
    
    /**
     * (�e�L�X�g�P�X)<BR>
     * �e�L�X�g�P�X
     */
    public String txt19;
    
    /**
     * (�e�L�X�g�Q�O)<BR>
     * �e�L�X�g�Q�O
     */
    public String txt20;
    
    /**
     * (�e�L�X�g�Q�P)<BR>
     * �e�L�X�g�Q�P
     */
    public String txt21;
    
    /**
     * (�e�L�X�g�Q�Q)<BR>
     * �e�L�X�g�Q�Q
     */
    public String txt22;
    
    /**
     * (�e�L�X�g�Q�R)<BR>
     * �e�L�X�g�Q�R
     */
    public String txt23;
    
    /**
     * (�e�L�X�g�Q�S)<BR>
     * �e�L�X�g�Q�S
     */
    public String txt24;
    
    /**
     * (�e�L�X�g�Q�T)<BR>
     * �e�L�X�g�Q�T
     */
    public String txt25;
    
    /**
     * (�e�L�X�g�Q�U)<BR>
     * �e�L�X�g�Q�U
     */
    public String txt26;
    
    /**
     * (�e�L�X�g�Q�V)<BR>
     * �e�L�X�g�Q�V
     */
    public String txt27;
    
    /**
     * (�e�L�X�g�Q�W)<BR>
     * �e�L�X�g�Q�W
     */
    public String txt28;
    
    /**
     * (�e�L�X�g�Q�X)<BR>
     * �e�L�X�g�Q�X
     */
    public String txt29;
    
    /**
     * (�e�L�X�g�R�O)<BR>
     * �e�L�X�g�R�O
     */
    public String txt30;
    
    /**
     * (�e�L�X�g�R�P)<BR>
     * �e�L�X�g�R�P
     */
    public String txt31;
    
    /**
     * (�e�L�X�g�R�Q)<BR>
     * �e�L�X�g�R�Q
     */
    public String txt32;
    
    /**
     * (�e�L�X�g�R�R)<BR>
     * �e�L�X�g�R�R
     */
    public String txt33;
    
    /**
     * (�e�L�X�g�R�S)<BR>
     * �e�L�X�g�R�S
     */
    public String txt34;
    
    /**
     * (�e�L�X�g�R�T)<BR>
     * �e�L�X�g�R�T
     */
    public String txt35;
    
    /**
     * (�e�L�X�g�R�U)<BR>
     * �e�L�X�g�R�U
     */
    public String txt36;
    
    /**
     * (�e�L�X�g�R�V)<BR>
     * �e�L�X�g�R�V
     */
    public String txt37;
    
    /**
     * (�e�L�X�g�R�W)<BR>
     * �e�L�X�g�R�W
     */
    public String txt38;
    
    /**
     * (�e�L�X�g�R�X)<BR>
     * �e�L�X�g�R�X
     */
    public String txt39;
    
    /**
     * (�e�L�X�g�S�O)<BR>
     * �e�L�X�g�S�O
     */
    public String txt40;
    
    /**
     * (���l�P)<BR>
     * ���l�P
     */
    public String num1;
    
    /**
     * (���l�Q)<BR>
     * ���l�Q
     */
    public String num2;
    
    /**
     * (���l�R)<BR>
     * ���l�R
     */
    public String num3;
    
    /**
     * (���l�S)<BR>
     * ���l�S
     */
    public String num4;
    
    /**
     * (���l�T)<BR>
     * ���l�T
     */
    public String num5;
    
    /**
     * (���l�U)<BR>
     * ���l�U
     */
    public String num6;
    
    /**
     * (���l�V)<BR>
     * ���l�V
     */
    public String num7;
    
    /**
     * (���l�W)<BR>
     * ���l�W
     */
    public String num8;
    
    /**
     * (���l�X)<BR>
     * ���l�X
     */
    public String num9;
    
    /**
     * (���l�P�O)<BR>
     * ���l�P�O
     */
    public String num10;
    
    /**
     * (���l�P�P)<BR>
     * ���l�P�P
     */
    public String num11;
    
    /**
     * (���l�P�Q)<BR>
     * ���l�P�Q
     */
    public String num12;
    
    /**
     * (���l�P�R)<BR>
     * ���l�P�R
     */
    public String num13;
    
    /**
     * (���l�P�S)<BR>
     * ���l�P�S
     */
    public String num14;
    
    /**
     * (���l�P�T)<BR>
     * ���l�P�T
     */
    public String num15;
    
    /**
     * (���l�P�U)<BR>
     * ���l�P�U
     */
    public String num16;
    
    /**
     * (���l�P�V)<BR>
     * ���l�P�V
     */
    public String num17;
    
    /**
     * (���l�P�W)<BR>
     * ���l�P�W
     */
    public String num18;
    
    /**
     * (���l�P�X)<BR>
     * ���l�P�X
     */
    public String num19;
    
    /**
     * (���l�Q�O)<BR>
     * ���l�Q�O
     */
    public String num20;
    
    /**
     * (���l�Q�P)<BR>
     * ���l�Q�P
     */
    public String num21;
    
    /**
     * (���l�Q�Q)<BR>
     * ���l�Q�Q
     */
    public String num22;
    
    /**
     * (���l�Q�R)<BR>
     * ���l�Q�R
     */
    public String num23;
    
    /**
     * (���l�Q�S)<BR>
     * ���l�Q�S
     */
    public String num24;
    
    /**
     * (���l�Q�T)<BR>
     * ���l�Q�T
     */
    public String num25;
    
    /**
     * (���l�Q�U)<BR>
     * ���l�Q�U
     */
    public String num26;
    
    /**
     * (���l�Q�V)<BR>
     * ���l�Q�V
     */
    public String num27;
    
    /**
     * (���l�Q�W)<BR>
     * ���l�Q�W
     */
    public String num28;
    
    /**
     * (���l�Q�X)<BR>
     * ���l�Q�X
     */
    public String num29;
    
    /**
     * (���l�R�O)<BR>
     * ���l�R�O
     */
    public String num30;
    
    /**
     * (���l�P)<BR>
     * ���l�P
     */
    public String remark1;
    
    /**
     * (���l�Q)<BR>
     * ���l�Q
     */
    public String remark2;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;

    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h
     */
    public String traderCode;

    /**
     * (�`�[�쐬���)<BR>
     * �`�[�쐬���
     */
    public String voucherInfo;

    /**
     * (�G���[���R�R�[�h)<BR>
     * �G���[���R�R�[�h
     */
    public String errorReasonCode;

    /**
     * (�`�[���ʃR�[�h)<BR>
     * �`�[���ʃR�[�h
     */
    public String voucherRequestNumber;

    /**
     * (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h
     */
    public String dataCode;

    /**
     * (�`�[���M����)<BR>
     * �`�[���M����
     */
    public Date voucherSendDate;

    /**
     * (�`�[��M����)<BR>
     * �`�[��M����
     */
    public Date voucherRecvDate;

    /**
     * (�e��A�����)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �e���ڂɁA����.�e��A���s�I�u�W�F�N�g�̓����ڂ̒l���Z�b�g����B<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g<BR>
     * @@roseuid 41C167D000BC
     */
    public WEB3InformDetailInfoUnit(VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3InformDetailInfoUnit()";
        log.entering(STR_METHOD_NAME);

        // �A�����
        this.informType = l_variousInformParams.getInformDiv();
    
        // �،���ЃR�[�h
        this.institutionCode = l_variousInformParams.getInstitutionCode();
    
        // ���X�R�[�h
        this.branchCode = l_variousInformParams.getBranchCode();
    
        log.debug("�،���ЃR�[�h:" + this.institutionCode);
        log.debug("���X�R�[�h:" + this.branchCode);
        log.debug("�����R�[�h(��o�O):" + l_variousInformParams.getAccountCode());

        // �����ԍ�
        // ����.�e��A��Params.�ڋq�R�[�h��null�łȂ��ꍇ�A��6�����Z�b�g����B
        if (WEB3StringTypeUtility.isNotEmpty(l_variousInformParams.getAccountCode()))
        {
            this.accountNumber = l_variousInformParams.getAccountCode().substring(0, 6);
        }
        log.debug("�����R�[�h(��o��):" + this.accountNumber);
    
        // �ڋq��
        this.accountName = l_variousInformParams.getAccountName();
    
        // ���[���A�h���X
        this.mailAddress = l_variousInformParams.getEmailAddress();
    
        // �敪�P
        this.div1 = l_variousInformParams.getExtDiv1();
    
        // �敪�Q
        this.div2 = l_variousInformParams.getExtDiv2();
    
        // �敪�R
        this.div3 = l_variousInformParams.getExtDiv3();
    
        // �敪�S
        this.div4 = l_variousInformParams.getExtDiv4();
    
        // �敪�T
        this.div5 = l_variousInformParams.getExtDiv5();
    
        // �敪�U
        this.div6 = l_variousInformParams.getExtDiv6();

        // �敪�V
        this.div7 = l_variousInformParams.getExtDiv7();
    
        // �敪�W
        this.div8 = l_variousInformParams.getExtDiv8();
    
        // �敪�X
        this.div9 = l_variousInformParams.getExtDiv9();

        // �敪�P�O
        this.div10 = l_variousInformParams.getExtDiv10();
    
        // �敪�P�P
        this.div11 = l_variousInformParams.getExtDiv11();
    
        // �敪�P�Q
        this.div12 = l_variousInformParams.getExtDiv12();

        // �敪�P�R
        this.div13 = l_variousInformParams.getExtDiv13();
    
        // �敪�P�S
        this.div14 = l_variousInformParams.getExtDiv14();
    
        // �敪�P�T
        this.div15 = l_variousInformParams.getExtDiv15();
    
        // �敪�P�U
        this.div16 = l_variousInformParams.getExtDiv16();

        // �敪�P�V
        this.div17 = l_variousInformParams.getExtDiv17();
    
        // �敪�P�W
        this.div18 = l_variousInformParams.getExtDiv18();
    
        // �敪�P�X
        this.div19 = l_variousInformParams.getExtDiv19();
    
        // �敪�Q�O
        this.div20 = l_variousInformParams.getExtDiv20();
    
        // �敪�Q�P
        this.div21 = l_variousInformParams.getExtDiv21();
    
        // �敪�Q�Q
        this.div22 = l_variousInformParams.getExtDiv22();
    
        // �敪�Q�R
        this.div23 = l_variousInformParams.getExtDiv23();
    
        // �敪�Q�S
        this.div24 = l_variousInformParams.getExtDiv24();
    
        // �敪�Q�T
        this.div25 = l_variousInformParams.getExtDiv25();
    
        // �敪�Q�U
        this.div26 = l_variousInformParams.getExtDiv26();
    
        // �敪�Q�V
        this.div27 = l_variousInformParams.getExtDiv27();
    
        // �敪�Q�W
        this.div28 = l_variousInformParams.getExtDiv28();
    
        // �敪�Q�X
        this.div29 = l_variousInformParams.getExtDiv29();
    
        // �敪�R�O
        this.div30 = l_variousInformParams.getExtDiv30();

        // �敪�R�P
        this.div31 = l_variousInformParams.getExtDiv31();

        // �敪�R�Q
        this.div32 = l_variousInformParams.getExtDiv32();
    
        // �敪�R�R
        this.div33 = l_variousInformParams.getExtDiv33();
    
        // �敪�R�S
        this.div34 = l_variousInformParams.getExtDiv34();
    
        // �敪�R�T
        this.div35 = l_variousInformParams.getExtDiv35();
    
        // �敪�R�U
        this.div36 = l_variousInformParams.getExtDiv36();
    
        // �敪�R�V
        this.div37 = l_variousInformParams.getExtDiv37();
    
        // �敪�R�W
        this.div38 = l_variousInformParams.getExtDiv38();
    
        // �敪�R�X
        this.div39 = l_variousInformParams.getExtDiv39();
    
        // �敪�S�O
        this.div40 = l_variousInformParams.getExtDiv40();
    
        // �R�[�h�P
        this.code1 = l_variousInformParams.getExtCode1();
    
        // �R�[�h�Q
        this.code2 = l_variousInformParams.getExtCode2();
    
        // �R�[�h�R
        this.code3 = l_variousInformParams.getExtCode3();
    
        // �R�[�h�S
        this.code4 = l_variousInformParams.getExtCode4();
    
        // �R�[�h�T
        this.code5 = l_variousInformParams.getExtCode5();
    
        // �R�[�h�U
        this.code6 = l_variousInformParams.getExtCode6();
    
        // �R�[�h�V
        this.code7 = l_variousInformParams.getExtCode7();
    
        // �R�[�h�W
        this.code8 = l_variousInformParams.getExtCode8();
    
        // �R�[�h�X
        this.code9 = l_variousInformParams.getExtCode9();
    
        // �R�[�h�P�O
        this.code10 = l_variousInformParams.getExtCode10();
    
        // �e�L�X�g�P
        this.txt1 = l_variousInformParams.getExtText1();
    
        // �e�L�X�g�Q
        this.txt2 = l_variousInformParams.getExtText2();
    
        // �e�L�X�g�R
        this.txt3 = l_variousInformParams.getExtText3();
    
        // �e�L�X�g�S
        this.txt4 = l_variousInformParams.getExtText4();
    
        // �e�L�X�g�T
        this.txt5 = l_variousInformParams.getExtText5();
    
        // �e�L�X�g�U
        this.txt6 = l_variousInformParams.getExtText6();
    
        // �e�L�X�g�V
        this.txt7 = l_variousInformParams.getExtText7();
    
        // �e�L�X�g�W
        this.txt8 = l_variousInformParams.getExtText8();
    
        // �e�L�X�g�X
        this.txt9 = l_variousInformParams.getExtText9();
    
        // �e�L�X�g�P�O
        this.txt10 = l_variousInformParams.getExtText10();
    
        // �e�L�X�g�P�P
        this.txt11 = l_variousInformParams.getExtText11();
    
        // �e�L�X�g�P�Q
        this.txt12 = l_variousInformParams.getExtText12();
    
        // �e�L�X�g�P�R
        this.txt13 = l_variousInformParams.getExtText13();
    
        // �e�L�X�g�P�S
        this.txt14 = l_variousInformParams.getExtText14();
    
        // �e�L�X�g�P�T
        this.txt15 = l_variousInformParams.getExtText15();
    
        // �e�L�X�g�P�U
        this.txt16 = l_variousInformParams.getExtText16();
    
        // �e�L�X�g�P�V
        this.txt17 = l_variousInformParams.getExtText17();
    
        // �e�L�X�g�P�W
        this.txt18 = l_variousInformParams.getExtText18();
    
        // �e�L�X�g�P�X
        this.txt19 = l_variousInformParams.getExtText19();
    
        // �e�L�X�g�Q�O
        this.txt20 = l_variousInformParams.getExtText20();
    
        // �e�L�X�g�Q�P
        this.txt21 = l_variousInformParams.getExtText21();
    
        // �e�L�X�g�Q�Q
        this.txt22 = l_variousInformParams.getExtText22();
    
        // �e�L�X�g�Q�R
        this.txt23 = l_variousInformParams.getExtText23();
    
        // �e�L�X�g�Q�S
        this.txt24 = l_variousInformParams.getExtText24();
    
        // �e�L�X�g�Q�T
        this.txt25 = l_variousInformParams.getExtText25();
    
        // �e�L�X�g�Q�U
        this.txt26 = l_variousInformParams.getExtText26();
    
        // �e�L�X�g�Q�V
        this.txt27 = l_variousInformParams.getExtText27();
    
        // �e�L�X�g�Q�W
        this.txt28 = l_variousInformParams.getExtText28();
    
        // �e�L�X�g�Q�X
        this.txt29 = l_variousInformParams.getExtText29();
    
        // �e�L�X�g�R�O
        this.txt30 = l_variousInformParams.getExtText30();
    
        // �e�L�X�g�R�P
        this.txt31 = l_variousInformParams.getExtText31();
    
        // �e�L�X�g�R�Q
        this.txt32 = l_variousInformParams.getExtText32();
    
        // �e�L�X�g�R�R
        this.txt33 = l_variousInformParams.getExtText33();
    
        // �e�L�X�g�R�S
        this.txt34 = l_variousInformParams.getExtText34();
    
        // �e�L�X�g�R�T
        this.txt35 = l_variousInformParams.getExtText35();
    
        // �e�L�X�g�R�U
        this.txt36 = l_variousInformParams.getExtText36();
    
        // �e�L�X�g�R�V
        this.txt37 = l_variousInformParams.getExtText37();
    
        // �e�L�X�g�R�W
        this.txt38 = l_variousInformParams.getExtText38();
    
        // �e�L�X�g�R�X
        this.txt39 = l_variousInformParams.getExtText39();
    
        // �e�L�X�g�S�O
        this.txt40 = l_variousInformParams.getExtText40();
    
        // ���l�P
        if (!l_variousInformParams.getExtValue1IsNull())
        {
            this.num1 = "" + l_variousInformParams.getExtValue1();
        }
    
        // ���l�Q
        if (!l_variousInformParams.getExtValue2IsNull())
        {
            this.num2 = "" + l_variousInformParams.getExtValue2();
        }    
    
        // ���l�R
        if (!l_variousInformParams.getExtValue3IsNull())
        {
            this.num3 = "" + l_variousInformParams.getExtValue3();
        }    
    
        // ���l�S
        if (!l_variousInformParams.getExtValue4IsNull())
        {
            this.num4 = "" + l_variousInformParams.getExtValue4();
        }    
    
        // ���l�T
        if (!l_variousInformParams.getExtValue5IsNull())
        {
            this.num5 = "" + l_variousInformParams.getExtValue5();
        }    
    
        // ���l�U
        if (!l_variousInformParams.getExtValue6IsNull())
        {
            this.num6 = "" + l_variousInformParams.getExtValue6();
        }    
    
        // ���l�V
        if (!l_variousInformParams.getExtValue7IsNull())
        {
            this.num7 = "" + l_variousInformParams.getExtValue7();
        }    
    
        // ���l�W
        if (!l_variousInformParams.getExtValue8IsNull())
        {
            this.num8 = "" + l_variousInformParams.getExtValue8();
        }    
    
        // ���l�X
        if (!l_variousInformParams.getExtValue9IsNull())
        {
            this.num9 = "" + l_variousInformParams.getExtValue9();
        }    
    
        // ���l�P�O
        if (!l_variousInformParams.getExtValue10IsNull())
        {
            this.num10 = "" + l_variousInformParams.getExtValue10();
        }    
    
        // ���l�P�P
        if (!l_variousInformParams.getExtValue11IsNull())
        {
            this.num11 = "" + l_variousInformParams.getExtValue11();
        }    
    
        // ���l�P�Q
        if (!l_variousInformParams.getExtValue12IsNull())
        {
            this.num12 = "" + l_variousInformParams.getExtValue12();
        }    
    
        // ���l�P�R
        if (!l_variousInformParams.getExtValue13IsNull())
        {
            this.num13 = "" + l_variousInformParams.getExtValue13();
        }    
    
        // ���l�P�S
        if (!l_variousInformParams.getExtValue14IsNull())
        {
            this.num14 = "" + l_variousInformParams.getExtValue14();
        }    
    
        // ���l�P�T
        if (!l_variousInformParams.getExtValue15IsNull())
        {
            this.num15 = "" + l_variousInformParams.getExtValue15();
        }    
    
        // ���l�P�U
        if (!l_variousInformParams.getExtValue16IsNull())
        {
            this.num16 = "" + l_variousInformParams.getExtValue16();
        }    
    
        // ���l�P�V
        if (!l_variousInformParams.getExtValue17IsNull())
        {
            this.num17 = "" + l_variousInformParams.getExtValue17();
        }    
    
        // ���l�P�W
        if (!l_variousInformParams.getExtValue18IsNull())
        {
            this.num18 = "" + l_variousInformParams.getExtValue18();
        }    
    
        // ���l�P�X
        if (!l_variousInformParams.getExtValue19IsNull())
        {
            this.num19 = "" + l_variousInformParams.getExtValue19();
        }    
    
        // ���l�Q�O
        if (!l_variousInformParams.getExtValue20IsNull())
        {
            this.num20 = "" + l_variousInformParams.getExtValue20();
        }    
    
        // ���l�Q�P
        if (!l_variousInformParams.getExtValue21IsNull())
        {
            this.num21 = "" + l_variousInformParams.getExtValue21();
        }    
    
        // ���l�Q�Q
        if (!l_variousInformParams.getExtValue22IsNull())
        {
            this.num22 = "" + l_variousInformParams.getExtValue22();
        }    
    
        // ���l�Q�R
        if (!l_variousInformParams.getExtValue23IsNull())
        {
            this.num23 = "" + l_variousInformParams.getExtValue23();
        }    
    
        // ���l�Q�S
        if (!l_variousInformParams.getExtValue24IsNull())
        {
            this.num24 = "" + l_variousInformParams.getExtValue24();
        }    
    
        // ���l�Q�T
        if (!l_variousInformParams.getExtValue25IsNull())
        {
            this.num25 = "" + l_variousInformParams.getExtValue25();
        }    
    
        // ���l�Q�U
        if (!l_variousInformParams.getExtValue26IsNull())
        {
            this.num26 = "" + l_variousInformParams.getExtValue26();
        }    
    
        // ���l�Q�V
        if (!l_variousInformParams.getExtValue27IsNull())
        {
            this.num27 = "" + l_variousInformParams.getExtValue27();
        }    
    
        // ���l�Q�W
        if (!l_variousInformParams.getExtValue28IsNull())
        {
            this.num28 = "" + l_variousInformParams.getExtValue28();
        }    
    
        // ���l�Q�X
        if (!l_variousInformParams.getExtValue29IsNull())
        {
            this.num29 = "" + l_variousInformParams.getExtValue29();
        }    
    
        // ���l�R�O
        if (!l_variousInformParams.getExtValue30IsNull())
        {
            this.num30 = "" + l_variousInformParams.getExtValue30();
        }    

        // ���l�P
        this.remark1 = l_variousInformParams.getExtNote1();
    
        // ���l�Q
        this.remark2 = l_variousInformParams.getExtNote2();

        // �����R�[�h
        this.productCode = l_variousInformParams.getFundCode();

        // ���҃R�[�h
        this.traderCode = l_variousInformParams.getSonarTraderCode();

        // �`�[�쐬���
        this.voucherInfo = l_variousInformParams.getStatus();

        // �G���[���R�R�[�h
        this.errorReasonCode = l_variousInformParams.getErrorReasonCode();

        // �`�[���ʃR�[�h
        this.voucherRequestNumber = l_variousInformParams.getOrderRequestNumber();

        // �f�[�^�R�[�h
        this.dataCode = l_variousInformParams.getRequestCode();

        // �`�[���M����
        this.voucherSendDate = l_variousInformParams.getSendTimestamp();

        // �`�[��M����
        this.voucherRecvDate = l_variousInformParams.getReceiptTimestamp();
     
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�jthis.�A����� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01817<BR>
     * <BR>
     * �Q�jthis.�،���ЃR�[�h == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00827<BR>
     * <BR>
     * �R�jthis.���l�P�`this.���l�R�O�̍��ڂ��ȉ��̏����̏ꍇ�A<BR>
     *      ��O���X���[����B<BR>
     *      ���ڂ�null�łȂ� and �����ȊO���Z�b�g����Ă���<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01947<BR>
     * <BR>
     * @@roseuid 41C167D000BC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // this.�A����� == null �̏ꍇ�A��O���X���[����
        if (this.informType == null)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + STR_METHOD_NAME,
                "�A�����.�A����ʂ�null�̒l�ł���B");
        }

        // �Q�jthis.�،���ЃR�[�h == null �̏ꍇ�A��O���X���[����
        if (this.institutionCode == null)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + STR_METHOD_NAME,
                "�،���ЃR�[�h�����w��ł��B");
        }

        // �R�jthis.���l�P�`this.���l�R�O�̍��ڂ��ȉ��̏����̏ꍇ�A
        //     ��O���X���[����
        if (this.num1 != null 
            && !WEB3StringTypeUtility.isDigit(this.num1))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num2 != null 
            && !WEB3StringTypeUtility.isDigit(this.num2))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num3 != null 
            && !WEB3StringTypeUtility.isDigit(this.num3))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�R���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num4 != null 
            && !WEB3StringTypeUtility.isDigit(this.num4))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�S���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num5 != null 
            && !WEB3StringTypeUtility.isDigit(this.num5))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�T���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num6 != null 
            && !WEB3StringTypeUtility.isDigit(this.num6))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�U���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num7 != null 
            && !WEB3StringTypeUtility.isDigit(this.num7))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�V���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num8 != null 
            && !WEB3StringTypeUtility.isDigit(this.num8))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�W���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num9 != null 
            && !WEB3StringTypeUtility.isDigit(this.num9))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�X���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num10 != null 
            && !WEB3StringTypeUtility.isDigit(this.num10))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�O���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num11 != null 
            && !WEB3StringTypeUtility.isDigit(this.num11))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�P���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num12 != null 
            && !WEB3StringTypeUtility.isDigit(this.num12))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�Q���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num13 != null 
            && !WEB3StringTypeUtility.isDigit(this.num13))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�R���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num14 != null 
            && !WEB3StringTypeUtility.isDigit(this.num14))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�S���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num15 != null 
            && !WEB3StringTypeUtility.isDigit(this.num15))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�T���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num16 != null 
            && !WEB3StringTypeUtility.isDigit(this.num16))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�U���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num17 != null 
            && !WEB3StringTypeUtility.isDigit(this.num17))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�V���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num18 != null 
            && !WEB3StringTypeUtility.isDigit(this.num18))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�W���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num19 != null 
            && !WEB3StringTypeUtility.isDigit(this.num19))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�P�X���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num20 != null 
            && !WEB3StringTypeUtility.isDigit(this.num20))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�O���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num21 != null 
            && !WEB3StringTypeUtility.isDigit(this.num21))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�P���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num22 != null 
            && !WEB3StringTypeUtility.isDigit(this.num22))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�Q���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num23 != null 
            && !WEB3StringTypeUtility.isDigit(this.num23))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�R���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num24 != null 
            && !WEB3StringTypeUtility.isDigit(this.num24))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�S���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num25 != null 
            && !WEB3StringTypeUtility.isDigit(this.num25))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�T���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num26 != null 
            && !WEB3StringTypeUtility.isDigit(this.num26))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�U���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num27 != null 
            && !WEB3StringTypeUtility.isDigit(this.num27))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�V���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num28 != null 
            && !WEB3StringTypeUtility.isDigit(this.num28))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�W���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num29 != null 
            && !WEB3StringTypeUtility.isDigit(this.num29))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�Q�X���ڂ����p�����ȊO�̒l�ł��B");
        }

        if (this.num30 != null 
            && !WEB3StringTypeUtility.isDigit(this.num30))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂̐��l�R�O���ڂ����p�����ȊO�̒l�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�e��A�����)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 41BE80DA0161
     */
    public WEB3InformDetailInfoUnit() 
    {
     
    }
}
@
