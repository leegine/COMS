head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3Inform.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �e��A��(WEB3Inform.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
Revesion History : 2007/06/06 �Ӑ�(���u) �C�� ���f��No.056�ANo.068
Revesion History : 2007/06/08 ���G��(���u) �C�� ���f��No.077
Revesion History : 2007/06/19 ���n�m(���u) �C��
Revesion History : 2008/02/19 �đo�g(���u) ���f��No.122
*/
package webbroker3.inform;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.bd.WEB3BondProductManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3ExtDiv1Def;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.inform.data.VariousInformPK;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3InformAddInfoUnit;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.inform.util.WEB3InformColumnSpec;
import webbroker3.inform.util.WEB3InformTableSpec;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (�e��A��)<BR>
 * �e��A���N���X<BR>
 */
public class WEB3Inform implements BusinessObject
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3Inform.class);
    /**
     * (�e��A���s)<BR>
     * �e��A���s�I�u�W�F�N�g<BR>
     */
    private VariousInformParams variousInformParams;

    /**
     * @@roseuid 41EE642C0177
     */
    public WEB3Inform()
    {

    }

    /**
     * (�e��A��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j��̊e��A���s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j����.�e��A�����̊e���ڂ̒l���e��A���s�I�u�W�F�N�g�̓����ڂɃZ�b�g����B<BR>
     * <BR>
     * �R�j�s�I�u�W�F�N�g��this.�e��A���s�ɃZ�b�g����B<BR>
     * @@param l_informDetailInfoUnit - (�A�����)
     * �e��A�����I�u�W�F�N�g
     * @@roseuid 41BD39FE0053
     */
    public WEB3Inform(WEB3InformDetailInfoUnit l_informDetailInfoUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3Inform(WEB3InformDetailInfoUnit l_informDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);
        VariousInformParams l_variousInformParams = new VariousInformParams();
        // �A�����
        l_variousInformParams.setInformDiv(l_informDetailInfoUnit.informType);

        // �،���ЃR�[�h
        l_variousInformParams.setInstitutionCode(l_informDetailInfoUnit.institutionCode);

        // ���X�R�[�h
        l_variousInformParams.setBranchCode(l_informDetailInfoUnit.branchCode);

        // �����ԍ�
        l_variousInformParams.setAccountCode(l_informDetailInfoUnit.accountNumber);

        // �ڋq��
        l_variousInformParams.setAccountName(l_informDetailInfoUnit.accountName);

        // ���[���A�h���X
        l_variousInformParams.setEmailAddress(l_informDetailInfoUnit.mailAddress);

        // �敪�P
        l_variousInformParams.setExtDiv1(l_informDetailInfoUnit.div1);

        // �敪�Q
        l_variousInformParams.setExtDiv2(l_informDetailInfoUnit.div2);

        // �敪�R
        l_variousInformParams.setExtDiv3(l_informDetailInfoUnit.div3);

        // �敪�S
        l_variousInformParams.setExtDiv4(l_informDetailInfoUnit.div4);

        // �敪�T
        l_variousInformParams.setExtDiv5(l_informDetailInfoUnit.div5);

        // �敪�U
        l_variousInformParams.setExtDiv6(l_informDetailInfoUnit.div6);

        // �敪�V
        l_variousInformParams.setExtDiv7(l_informDetailInfoUnit.div7);

        // �敪�W
        l_variousInformParams.setExtDiv8(l_informDetailInfoUnit.div8);

        // �敪�X
        l_variousInformParams.setExtDiv9(l_informDetailInfoUnit.div9);

        // �敪�P�O
        l_variousInformParams.setExtDiv10(l_informDetailInfoUnit.div10);

        // �敪�P�P
        l_variousInformParams.setExtDiv11(l_informDetailInfoUnit.div11);

        // �敪�P�Q
        l_variousInformParams.setExtDiv12(l_informDetailInfoUnit.div12);

        // �敪�P�R
        l_variousInformParams.setExtDiv13(l_informDetailInfoUnit.div13);

        // �敪�P�S
        l_variousInformParams.setExtDiv14(l_informDetailInfoUnit.div14);

        // �敪�P�T
        l_variousInformParams.setExtDiv15(l_informDetailInfoUnit.div15);

        // �敪�P�U
        l_variousInformParams.setExtDiv16(l_informDetailInfoUnit.div16);

        // �敪�P�V
        l_variousInformParams.setExtDiv17(l_informDetailInfoUnit.div17);

        // �敪�P�W
        l_variousInformParams.setExtDiv18(l_informDetailInfoUnit.div18);

        // �敪�P�X
        l_variousInformParams.setExtDiv19(l_informDetailInfoUnit.div19);

        // �敪�Q�O
        l_variousInformParams.setExtDiv20(l_informDetailInfoUnit.div20);

        // �敪�Q�P
        l_variousInformParams.setExtDiv21(l_informDetailInfoUnit.div21);

        // �敪�Q�Q
        l_variousInformParams.setExtDiv22(l_informDetailInfoUnit.div22);

        // �敪�Q�R
        l_variousInformParams.setExtDiv23(l_informDetailInfoUnit.div23);

        // �敪�Q�S
        l_variousInformParams.setExtDiv24(l_informDetailInfoUnit.div24);

        // �敪�Q�T
        l_variousInformParams.setExtDiv25(l_informDetailInfoUnit.div25);

        // �敪�Q�U
        l_variousInformParams.setExtDiv26(l_informDetailInfoUnit.div26);

        // �敪�Q�V
        l_variousInformParams.setExtDiv27(l_informDetailInfoUnit.div27);

        // �敪�Q�W
        l_variousInformParams.setExtDiv28(l_informDetailInfoUnit.div28);

        // �敪�Q�X
        l_variousInformParams.setExtDiv29(l_informDetailInfoUnit.div29);

        // �敪�R�O
        l_variousInformParams.setExtDiv30(l_informDetailInfoUnit.div30);

        // �敪�R�P
        l_variousInformParams.setExtDiv31(l_informDetailInfoUnit.div31);

        // �敪�R�Q
        l_variousInformParams.setExtDiv32(l_informDetailInfoUnit.div32);

        // �敪�R�R
        l_variousInformParams.setExtDiv33(l_informDetailInfoUnit.div33);

        // �敪�R�S
        l_variousInformParams.setExtDiv34(l_informDetailInfoUnit.div34);

        // �敪�R�T
        l_variousInformParams.setExtDiv35(l_informDetailInfoUnit.div35);

        // �敪�R�U
        l_variousInformParams.setExtDiv36(l_informDetailInfoUnit.div36);

        // �敪�R�V
        l_variousInformParams.setExtDiv37(l_informDetailInfoUnit.div37);

        // �敪�R�W
        l_variousInformParams.setExtDiv38(l_informDetailInfoUnit.div38);

        // �敪�R�X
        l_variousInformParams.setExtDiv39(l_informDetailInfoUnit.div39);

        // �敪�S�O
        l_variousInformParams.setExtDiv40(l_informDetailInfoUnit.div40);

        // �R�[�h�P
        l_variousInformParams.setExtCode1(l_informDetailInfoUnit.code1);

        // �R�[�h�Q
        l_variousInformParams.setExtCode2(l_informDetailInfoUnit.code2);

        // �R�[�h�R
        l_variousInformParams.setExtCode3(l_informDetailInfoUnit.code3);

        // �R�[�h�S
        l_variousInformParams.setExtCode4(l_informDetailInfoUnit.code4);

        // �R�[�h�T
        l_variousInformParams.setExtCode5(l_informDetailInfoUnit.code5);

        // �R�[�h�U
        l_variousInformParams.setExtCode6(l_informDetailInfoUnit.code6);

        // �R�[�h�V
        l_variousInformParams.setExtCode7(l_informDetailInfoUnit.code7);

        // �R�[�h�W
        l_variousInformParams.setExtCode8(l_informDetailInfoUnit.code8);

        // �R�[�h�X
        l_variousInformParams.setExtCode9(l_informDetailInfoUnit.code9);

        // �R�[�h�P�O
        l_variousInformParams.setExtCode10(l_informDetailInfoUnit.code10);

        // �e�L�X�g�P
        l_variousInformParams.setExtText1(l_informDetailInfoUnit.txt1);

        // �e�L�X�g�Q
        l_variousInformParams.setExtText2(l_informDetailInfoUnit.txt2);

        // �e�L�X�g�R
        l_variousInformParams.setExtText3(l_informDetailInfoUnit.txt3);

        // �e�L�X�g�S
        l_variousInformParams.setExtText4(l_informDetailInfoUnit.txt4);

        // �e�L�X�g�T
        l_variousInformParams.setExtText5(l_informDetailInfoUnit.txt5);

        // �e�L�X�g�U
        l_variousInformParams.setExtText6(l_informDetailInfoUnit.txt6);

        // �e�L�X�g�V
        l_variousInformParams.setExtText7(l_informDetailInfoUnit.txt7);

        // �e�L�X�g�W
        l_variousInformParams.setExtText8(l_informDetailInfoUnit.txt8);

        // �e�L�X�g�X
        l_variousInformParams.setExtText9(l_informDetailInfoUnit.txt9);

        // �e�L�X�g�P�O
        l_variousInformParams.setExtText10(l_informDetailInfoUnit.txt10);

        // �e�L�X�g�P�P
        l_variousInformParams.setExtText11(l_informDetailInfoUnit.txt11);

        // �e�L�X�g�P�Q
        l_variousInformParams.setExtText12(l_informDetailInfoUnit.txt12);

        // �e�L�X�g�P�R
        l_variousInformParams.setExtText13(l_informDetailInfoUnit.txt13);

        // �e�L�X�g�P�S
        l_variousInformParams.setExtText14(l_informDetailInfoUnit.txt14);

        // �e�L�X�g�P�T
        l_variousInformParams.setExtText15(l_informDetailInfoUnit.txt15);

        // �e�L�X�g�P�U
        l_variousInformParams.setExtText16(l_informDetailInfoUnit.txt16);

        // �e�L�X�g�P�V
        l_variousInformParams.setExtText17(l_informDetailInfoUnit.txt17);

        // �e�L�X�g�P�W
        l_variousInformParams.setExtText18(l_informDetailInfoUnit.txt18);

        // �e�L�X�g�P�X
        l_variousInformParams.setExtText19(l_informDetailInfoUnit.txt19);

        // �e�L�X�g�Q�O
        l_variousInformParams.setExtText20(l_informDetailInfoUnit.txt20);

        // �e�L�X�g�Q�P
        l_variousInformParams.setExtText21(l_informDetailInfoUnit.txt21);

        // �e�L�X�g�Q�Q
        l_variousInformParams.setExtText22(l_informDetailInfoUnit.txt22);

        // �e�L�X�g�Q�R
        l_variousInformParams.setExtText23(l_informDetailInfoUnit.txt23);

        // �e�L�X�g�Q�S
        l_variousInformParams.setExtText24(l_informDetailInfoUnit.txt24);

        // �e�L�X�g�Q�T
        l_variousInformParams.setExtText25(l_informDetailInfoUnit.txt25);

        // �e�L�X�g�Q�U
        l_variousInformParams.setExtText26(l_informDetailInfoUnit.txt26);

        // �e�L�X�g�Q�V
        l_variousInformParams.setExtText27(l_informDetailInfoUnit.txt27);

        // �e�L�X�g�Q�W
        l_variousInformParams.setExtText28(l_informDetailInfoUnit.txt28);

        // �e�L�X�g�Q�X
        l_variousInformParams.setExtText29(l_informDetailInfoUnit.txt29);

        // �e�L�X�g�R�O
        l_variousInformParams.setExtText30(l_informDetailInfoUnit.txt30);

        // �e�L�X�g�R�P
        l_variousInformParams.setExtText31(l_informDetailInfoUnit.txt31);

        // �e�L�X�g�R�Q
        l_variousInformParams.setExtText32(l_informDetailInfoUnit.txt32);

        // �e�L�X�g�R�R
        l_variousInformParams.setExtText33(l_informDetailInfoUnit.txt33);

        // �e�L�X�g�R�S
        l_variousInformParams.setExtText34(l_informDetailInfoUnit.txt34);

        // �e�L�X�g�R�T
        l_variousInformParams.setExtText35(l_informDetailInfoUnit.txt35);

        // �e�L�X�g�R�U
        l_variousInformParams.setExtText36(l_informDetailInfoUnit.txt36);

        // �e�L�X�g�R�V
        l_variousInformParams.setExtText37(l_informDetailInfoUnit.txt37);

        // �e�L�X�g�R�W
        l_variousInformParams.setExtText38(l_informDetailInfoUnit.txt38);

        // �e�L�X�g�R�X
        l_variousInformParams.setExtText39(l_informDetailInfoUnit.txt39);

        // �e�L�X�g�S�O
        l_variousInformParams.setExtText40(l_informDetailInfoUnit.txt40);

        // ���l�P
        if (l_informDetailInfoUnit.num1 != null)
        {
            l_variousInformParams.setExtValue1(Long.parseLong(l_informDetailInfoUnit.num1));
        }
                


        // ���l�Q
        if (l_informDetailInfoUnit.num2 != null)
        {
            l_variousInformParams.setExtValue2(Long.parseLong(l_informDetailInfoUnit.num2));
        }


        // ���l�R
        if (l_informDetailInfoUnit.num3 != null)
        {
            l_variousInformParams.setExtValue3(Long.parseLong(l_informDetailInfoUnit.num3));
        }


        // ���l�S
        if (l_informDetailInfoUnit.num4 != null)
        {
            l_variousInformParams.setExtValue4(Long.parseLong(l_informDetailInfoUnit.num4));
        }

        // ���l�T
        if (l_informDetailInfoUnit.num5 != null)
        {
            l_variousInformParams.setExtValue5(Long.parseLong(l_informDetailInfoUnit.num5));
        }
        // ���l�U
        if (l_informDetailInfoUnit.num6 != null)
        {
            l_variousInformParams.setExtValue6(Long.parseLong(l_informDetailInfoUnit.num6));
        }
        // ���l�V
        if (l_informDetailInfoUnit.num7 != null)
        {
            l_variousInformParams.setExtValue7(Long.parseLong(l_informDetailInfoUnit.num7));
        }
        // ���l�W
        if (l_informDetailInfoUnit.num8 != null)
        {
            l_variousInformParams.setExtValue8(Long.parseLong(l_informDetailInfoUnit.num8));
        }
        // ���l�X
        if (l_informDetailInfoUnit.num9 != null)
        {
            l_variousInformParams.setExtValue9(Long.parseLong(l_informDetailInfoUnit.num9));
        }
        // ���l�P�O
        if (l_informDetailInfoUnit.num10 != null)
        {
            l_variousInformParams.setExtValue10(Long.parseLong(l_informDetailInfoUnit.num10));
        }
        // ���l�P�P
        if (l_informDetailInfoUnit.num11 != null)
        {
            l_variousInformParams.setExtValue11(Long.parseLong(l_informDetailInfoUnit.num11));
        }
        // ���l�P�Q
        if (l_informDetailInfoUnit.num12 != null)
        {
            l_variousInformParams.setExtValue12(Long.parseLong(l_informDetailInfoUnit.num12));
        }
        // ���l�P�R
        if (l_informDetailInfoUnit.num13 != null)
        {
            l_variousInformParams.setExtValue13(Long.parseLong(l_informDetailInfoUnit.num13));
        }
        // ���l�P�S
        if (l_informDetailInfoUnit.num14 != null)
        {
            l_variousInformParams.setExtValue14(Long.parseLong(l_informDetailInfoUnit.num14));
        }
        // ���l�P�T
        if (l_informDetailInfoUnit.num15 != null)
        {
            l_variousInformParams.setExtValue15(Long.parseLong(l_informDetailInfoUnit.num15));
        }
        // ���l�P�U
        if (l_informDetailInfoUnit.num16 != null)
        {
            l_variousInformParams.setExtValue16(Long.parseLong(l_informDetailInfoUnit.num16));
        }
        // ���l�P�V
        if (l_informDetailInfoUnit.num17 != null)
        {
            l_variousInformParams.setExtValue17(Long.parseLong(l_informDetailInfoUnit.num17));
        }
        // ���l�P�W
        if (l_informDetailInfoUnit.num18 != null)
        {
            l_variousInformParams.setExtValue18(Long.parseLong(l_informDetailInfoUnit.num18));
        }
        // ���l�P�X
        if (l_informDetailInfoUnit.num19 != null)
        {
            l_variousInformParams.setExtValue19(Long.parseLong(l_informDetailInfoUnit.num19));
        }
        // ���l�Q�O
        if (l_informDetailInfoUnit.num20 != null)
        {
            l_variousInformParams.setExtValue20(Long.parseLong(l_informDetailInfoUnit.num20));
        }
        // ���l�Q�P
        if (l_informDetailInfoUnit.num21 != null)
        {
            l_variousInformParams.setExtValue21(Long.parseLong(l_informDetailInfoUnit.num21));
        }
        // ���l�Q�Q
        if (l_informDetailInfoUnit.num22 != null)
        {
            l_variousInformParams.setExtValue22(Long.parseLong(l_informDetailInfoUnit.num22));
        }
        // ���l�Q�R
        if (l_informDetailInfoUnit.num23 != null)
        {
            l_variousInformParams.setExtValue23(Long.parseLong(l_informDetailInfoUnit.num23));
        }
        // ���l�Q�S
        if (l_informDetailInfoUnit.num24 != null)
        {
            l_variousInformParams.setExtValue24(Long.parseLong(l_informDetailInfoUnit.num24));
        }
        // ���l�Q�T
        if (l_informDetailInfoUnit.num25 != null)
        {
            l_variousInformParams.setExtValue25(Long.parseLong(l_informDetailInfoUnit.num25));
        }
        // ���l�Q�U
        if (l_informDetailInfoUnit.num26 != null)
        {
            l_variousInformParams.setExtValue26(Long.parseLong(l_informDetailInfoUnit.num26));
        }
        // ���l�Q�V
        if (l_informDetailInfoUnit.num27 != null)
        {
            l_variousInformParams.setExtValue27(Long.parseLong(l_informDetailInfoUnit.num27));
        }
        // ���l�Q�W
        if (l_informDetailInfoUnit.num28 != null)
        {
            l_variousInformParams.setExtValue28(Long.parseLong(l_informDetailInfoUnit.num28));
        }
        // ���l�Q�X
        if (l_informDetailInfoUnit.num29 != null)
        {
            l_variousInformParams.setExtValue29(Long.parseLong(l_informDetailInfoUnit.num29));
        }
        // ���l�R�O
        if (l_informDetailInfoUnit.num30 != null)
        {
            l_variousInformParams.setExtValue30(Long.parseLong(l_informDetailInfoUnit.num30));
        }
        // ���l�P
        l_variousInformParams.setExtNote1(l_informDetailInfoUnit.remark1);

        // ���l�Q
        l_variousInformParams.setExtNote2(l_informDetailInfoUnit.remark2);

        // �����R�[�h
        l_variousInformParams.setFundCode(l_informDetailInfoUnit.productCode);

        // ���҃R�[�h�iSONAR�j
        l_variousInformParams.setSonarTraderCode(l_informDetailInfoUnit.traderCode);

        // �`�[�쐬��
        l_variousInformParams.setStatus(l_informDetailInfoUnit.voucherInfo);

        // �G���[���R�R�[�h
        l_variousInformParams.setErrorReasonCode(l_informDetailInfoUnit.errorReasonCode);

        // �`�[���ʃR�[�h
        l_variousInformParams.setOrderRequestNumber(l_informDetailInfoUnit.voucherRequestNumber);

        // �f�[�^�R�[�h
        l_variousInformParams.setRequestCode(l_informDetailInfoUnit.dataCode);

        // �`�[���M����
        l_variousInformParams.setSendTimestamp(l_informDetailInfoUnit.voucherSendDate);

        // �`�[��M����
        l_variousInformParams.setReceiptTimestamp(l_informDetailInfoUnit.voucherRecvDate);

        this.variousInformParams = l_variousInformParams;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�e��A�����)<BR>
     * �e��A���f�[�^�̓��̓`�F�b�N���s���B <BR>
     * <BR>
     * �e�ЃJ�X�^�}�C�Y������΁A�e��A�����ڃ}�X�^�e�[�u���Ɏw�肳�ꂽ�`�F�b�N���s���B <BR>
     * �e�ЃJ�X�^�}�C�Y���Ȃ���΁ADB���C�A�E�g �u�e��A���e�[�u��.xls�v�ɋL�q���ꂽ�f�t�H���g�`�F�b�N���s���B <BR>
     * <BR>
     * this.�e��A���s�̃`�F�b�N�Ώۍ���(*1)���ׂĂɂ��āA�ȉ��P�j�`�R�j�̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�e��A�����ڃ}�X�^���� <BR>
     * �@@�e��A�����ڃ}�X�^�𐶐�����B <BR>
     * �@@�����@@��null���ԋp���ꂽ�ꍇ�́A�����A�Ő�������B <BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈����@@] <BR>
     * �@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@���X�R�[�h�F�@@this.get���X�R�[�h()�̖߂�l<BR>
     * �@@�A����ʁF�@@this.get�A�����()�̖߂�l<BR>
     * �@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1) <BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈����A] <BR>
     * �@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@���X�R�[�h�F�@@�h000�h <BR>
     * �@@�A����ʁF�@@this.get�A�����()�̖߂�l<BR>
     * �@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1) <BR>
     * <BR>
     * �@@�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ��Ɣ��f���A <BR>
     * �@@�e��A�����ڃ}�X�^.getDefault���ڃ}�X�^()�ɂăC���X�^���X�𐶐�����B <BR>
     * <BR>
     * �@@[getDefault���ڃ}�X�^()�Ɏw�肷�����] <BR>
     * �@@�K�{���ڃt���O�F�@@�`�F�b�N�Ώۍ���(*1)���yNull�z���ڂł����BooleanEnum.FALSE�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�yNotNull�z���ڂł���΁ABooleanEnum.TRUE���w�肷��B <BR>
     * �@@���ڍő咷�F�@@�`�F�b�N�Ώۍ���(*1)�́ySIZE�z <BR>
     * �@@���ڃ`�F�b�N�����F�@@�`�F�b�N�Ώۍ���(*1)�́y���ڃ`�F�b�N�����iWEB3�f�t�H���g�j�z <BR>
     * <BR>
     * �Q�j�@@�K�{���ڃ`�F�b�N <BR>
     * �@@�P�j�Ő��������e��A�����ڃ}�X�^.validate�K�{����()���R�[������B <BR>
     * �@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01309<BR>
     * <BR>
     * �@@[validate�K�{����()�Ɏw�肷�����] <BR>
     * �@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l <BR>
     * <BR>
     *   validate�K�{����()�̖߂�l == true && ���ڒl == null �̏ꍇ�́A<BR>
     *   ����ȍ~�̃`�F�b�N�������X�L�b�v����B<BR>
     * <BR>    
     * �R�j�@@�f�[�^�����O�X�`�F�b�N <BR>
     * �@@�P�j�Ő��������e��A�����ڃ}�X�^.validate�����O�X()���R�[������B <BR>
     * �@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01310<BR>
     * <BR>
     * �@@[validate�����O�X()�Ɏw�肷�����] <BR>
     * �@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l <BR>
     * <BR>
     * �S�j�@@�L���l�`�F�b�N <BR>
     * �@@���@@�L���R�[�h�l�`�F�b�N�̏ꍇ�iif(�e��A�����ڃ}�X�^.is�L���R�[�h�`�F�b�N() == true)�j <BR>
     * <BR>
     * �@@�@@�e��A�����ڑ����I�u�W�F�N�g�𐶐�����B <BR>
     * �@@�@@�����@@��null���ԋp���ꂽ�ꍇ�́A�����A�Ő�������B <BR>
     * <BR>
     * �@@�@@[�R���X�g���N�^�̈����@@] <BR>
     * �@@�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@�@@���X�R�[�h�F�@@this.get���X�R�[�h()�̖߂�l<BR>
     * �@@�@@�A����ʁF�@@this.get�A�����()�̖߂�l<BR>
     * �@@�@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1)<BR>
     * <BR>
     * �@@�@@[�R���X�g���N�^�̈����A] <BR>
     * �@@�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@�@@���X�R�[�h�F�@@�h000�h<BR>
     * �@@�@@�A����ʁF�@@this.get�A�����()�̖߂�l<BR>
     * �@@�@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1)<BR>
     * <BR>
     * �@@�@@�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ��Ɣ��f��WEB3�f�t�H���g�`�F�b�N���s���B <BR>
     * �@@�@@�`�F�b�N�Ώۍ���(*1)�̒l�����݂���R�[�h�l�łȂ���΁A��O���X���[����B <BR>
     * �@@�@@���`�F�b�N�Ώۍ���(*1)�́y�����i�L���R�[�h�A�Ӗ��j�z�Q�ƁB <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01311<BR>
     * <BR>
     * �@@�@@�I�u�W�F�N�g�������ł����ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^������Ɣ��肵�A <BR>
     * �@@�@@�e��A�����ڑ���.validate�L���R�[�h�l()���R�[������B <BR>
     * <BR>
     * �@@�@@[validate�L���R�[�h�l()�Ɏw�肷�����] <BR>
     * �@@�@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l <BR>
     * <BR>
     * �@@���@@�����`�F�b�N�̏ꍇ�ielse if(�e��A�����ڃ}�X�^.is�����`�F�b�N() == true)�j<BR>
     *     this.validate����()���R�[������B<BR>
     * �@@�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01869<BR>
     * <BR>
     * �@@�@@[validate����()�Ɏw�肷�����] <BR>
     *     �،���ЁF this.get�،����()�̖߂�l<BR>
     *     �����R�[�h�F �`�F�b�N�Ώۍ���(*1)�̒l<BR>
     * <BR>
     * �@@���@@���M�����`�F�b�N�̏ꍇ�ielse if(�e��A�����ڃ}�X�^.is���M�����`�F�b�N() == true)�j<BR>
     * �@@�@@�@@this.validate���M����()���R�[������B<BR>
     * �@@�@@�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B<BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_02796<BR>
     * <BR>
     * �@@�@@�@@[validate���M����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЁF this.get�،����()�̖߂�l<BR>
     * �@@�@@�@@�����R�[�h�F �`�F�b�N�Ώۍ���(*1)�̒l<BR>
     * <BR>
     * �@@���@@�������`�F�b�N�̏ꍇ�ielse if(�e��A�����ڃ}�X�^.is�������`�F�b�N() == true)�j<BR>
     * �@@�@@�@@this.validate������()���R�[������B<BR>
     * �@@�@@�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B<BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@tag:BUSINESS_ERROR_02797<BR>
     * <BR>
     * �@@�@@�@@[validate������()�Ɏw�肷�����]
     * �@@�@@�@@�،���ЁF this.get�،����()�̖߂�l
     * �@@�@@�@@�����R�[�h�F �`�F�b�N�Ώۍ���(*1)�̒l
     * <BR>
     * �@@���@@��L�`�F�b�N�ȊO�ielse�j�̏ꍇ<BR>
     * �@@�@@�P�j�Ő��������e��A�����ڃ}�X�^.validate�L���l()���R�[������B <BR>
     * �@@�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01312<BR>
     * <BR>
     * �@@�@@[validate�L���l()�Ɏw�肷�����] <BR>
     * �@@�@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l <BR>
     * <BR>
     * <BR>
     * (*1) �`�F�b�N�Ώۍ��� <BR>
     * �@@DB���C�A�E�g �u�e��A���e�[�u��.xls�v����<BR>
     * �y���ڃ`�F�b�N�����z��ɋL�ڂ����鍀�ڂƃJ�X�^�}�C�Y�\�����ڂ��ΏہB�B <BR> 
     * @@roseuid 41BD3B87038F
     */
    public void validateInformDetailInfoUnit() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInformDetailInfoUnit()";
        log.entering(STR_METHOD_NAME);
        //�e�ЃJ�X�^�}�C�Y������΁A�e��A�����ڃ}�X�^�e�[�u���Ɏw�肳�ꂽ�`�F�b�N���s���B 
        //�e�ЃJ�X�^�}�C�Y���Ȃ���΁ADB���C�A�E�g �u�e��A���e�[�u��.xls�v�ɋL�q���ꂽ�f�t�H���g�`�F�b�N���s���B 
        //
        //this.�e��A���s�̃`�F�b�N�Ώۍ���(*1)���ׂĂɂ��āA�ȉ��P�j�`�S�j�̃`�F�b�N���s���B 
        //
        //�P�j�@@�e��A�����ڃ}�X�^���� 
        //�@@�e��A�����ڃ}�X�^�𐶐�����B 
        //�@@�����@@��null���ԋp���ꂽ�ꍇ�́A�����A�Ő�������B 
        //
        //�@@[�R���X�g���N�^�̈����@@] 
        //�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l
        //�@@���X�R�[�h�F�@@this.get���X�R�[�h()�̖߂�l
        //�@@�A����ʁF�@@this.get�A�����()�̖߂�l
        //�@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1) 
        WEB3InformItemMaster l_informItemMaster = null;
        WEB3InformTableSpec l_spec = new WEB3InformTableSpec();

        int l_intColumnLen = l_spec.getColumnSpecsByTableName(VariousInformRow.TYPE.getTableName()).length;
        String[] l_strItemSymbolNames = new String[l_intColumnLen];
        for (int i = 0; i < l_intColumnLen;i++)
        {
            WEB3InformColumnSpec l_loopSpec = l_spec.getColumnSpecsByTableName(VariousInformRow.TYPE.getTableName())[i];
            if (!l_loopSpec.isDefaultCheckModeSet() && !l_loopSpec.isCustomizeAble())
            {
                continue;
            }
            l_strItemSymbolNames[i] = l_loopSpec.asHeader();
            try
            {
                l_informItemMaster = new WEB3InformItemMaster(
                    this.getInstitutionCode(),
                    this.getBranchCode(),
                    this.getInformDiv(),
                    l_strItemSymbolNames[i]); 
            }
            catch (NotFoundException l_ex)
            {
                try
                {
                    //�@@[�R���X�g���N�^�̈����A] 
                    //�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l
                    //�@@���X�R�[�h�F�@@�h000�h 
                    //�@@�A����ʁF�@@this.get�A�����()�̖߂�l
                    //�@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1) 
                    l_informItemMaster = new WEB3InformItemMaster(
                        this.getInstitutionCode(),
                        WEB3BranchCodeDef.DEFAULT,
                        this.getInformDiv(),
                        l_strItemSymbolNames[i]); 
                }
                catch (NotFoundException l_ex1)
                {
                    //�@@�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ��Ɣ��f���A 
                    //�@@�e��A�����ڃ}�X�^.getDefault���ڃ}�X�^()�ɂăC���X�^���X�𐶐�����B
                    //�@@[getDefault���ڃ}�X�^()�Ɏw�肷�����] 
                    //�@@�K�{���ڃt���O�F�@@�`�F�b�N�Ώۍ���(*1)���yNull�z���ڂł����BooleanEnum.FALSE�A 
                    //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�yNotNull�z���ڂł���΁ABooleanEnum.TRUE���w�肷��B 
                    //�@@���ڍő咷�F�@@�`�F�b�N�Ώۍ���(*1)�́ySIZE�z 
                    //�@@���ڃ`�F�b�N�����F�@@�`�F�b�N�Ώۍ���(*1)�́y���ڃ`�F�b�N�����iWEB3�f�t�H���g�j�z 
                    // 

                    BooleanEnum l_boolEnum = null;
                    if (l_loopSpec.getIsNullable())
                    {
                        l_boolEnum = BooleanEnum.FALSE;
                    }
                    else
                    {
                        l_boolEnum = BooleanEnum.TRUE;
                    }
                    l_informItemMaster = WEB3InformItemMaster.getDefaultItemMaster(l_boolEnum,
                        l_loopSpec.columnSize(),l_loopSpec.getDefaultCheckMode());

                }
            }


            //�Q�j�@@�K�{���ڃ`�F�b�N 
            //�@@�P�j�Ő��������e��A�����ڃ}�X�^.validate�K�{����()���R�[������B 
            //�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B 
            //�@@class: WEB3BusinessLayerException
            //�@@tag: BUSINESS_ERROR_01309
            //
            //�@@[validate�K�{����()�Ɏw�肷�����] 
            //�@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l 
            //
            //InformCtrlItemMasterParams l_masterParams = (InformCtrlItemMasterParams)l_informItemMaster.getDataSourceObject();
            Object l_obj = variousInformParams.getColumn(l_loopSpec.asHeader());

            boolean l_necessaryItem = l_informItemMaster.validateNecessaryItem(l_obj);
            if (!l_necessaryItem)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_loopSpec.getColumnNameKana());
            }
            //�Evalidate�K�{����()�̖߂�l == true && ���ڒl == null �̏ꍇ�́A
            //  ����ȍ~�̃`�F�b�N�������X�L�b�v����B
            if (l_necessaryItem && l_obj == null)
            {
                continue;
            }
 
            //�R�j�@@�f�[�^�����O�X�`�F�b�N 
            //�@@�P�j�Ő��������e��A�����ڃ}�X�^.validate�����O�X()���R�[������B 
            //�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B 
            //�@@class: WEB3BusinessLayerException
            //�@@tag: BUSINESS_ERROR_01310
            //
            //�@@[validate�����O�X()�Ɏw�肷�����] 
            //�@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l 
            //
            boolean l_necessaryItemLen = l_informItemMaster.validateLength(l_obj);
            
            if (!l_necessaryItemLen)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01310,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_loopSpec.getColumnNameKana());
            }
            //�S�j�@@�L���l�`�F�b�N 
            //�@@���@@�L���R�[�h�l�`�F�b�N�̏ꍇ�iif(�e��A�����ڃ}�X�^.is�L���R�[�h�`�F�b�N() == true)�j 
            //
            //�@@�@@�e��A�����ڑ����I�u�W�F�N�g�𐶐�����B 
            //�@@�@@�����@@��null���ԋp���ꂽ�ꍇ�́A�����A�Ő�������B 
            //
            //�@@�@@[�R���X�g���N�^�̈����@@] 
            //�@@�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l
            //�@@�@@���X�R�[�h�F�@@this.get���X�R�[�h()�̖߂�l
            //�@@�@@�A����ʁF�@@this.get�A�����()�̖߂�l
            //�@@�@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1)
            //
            //�@@�@@[�R���X�g���N�^�̈����A] 
            //�@@�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l
            //�@@�@@���X�R�[�h�F�@@�h000�h
            //�@@�@@�A����ʁF�@@this.get�A�����()�̖߂�l
            //�@@�@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1)
            //
            WEB3InformItemProperty l_property = null;
            if (l_informItemMaster.isEffectiveCodeCheck())
            {
                try
                {
                    l_property = new WEB3InformItemProperty(
                        this.getInstitutionCode(),
                        this.getBranchCode(),
                        this.getInformDiv(),
                        l_strItemSymbolNames[i]); 
                }
                catch (NotFoundException l_ex)
                {
                    try
                    {
                        l_property = new WEB3InformItemProperty(
                            this.getInstitutionCode(),
                            WEB3BranchCodeDef.DEFAULT,
                            this.getInformDiv(),
                            l_strItemSymbolNames[i]); 
                    }
                    catch(NotFoundException l_ex1)
                    {


                    }
                }

                //�@@�@@�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ��Ɣ��f��WEB3�f�t�H���g�`�F�b�N���s���B 
                //�@@�@@�`�F�b�N�Ώۍ���(*1)�̒l�����݂���R�[�h�l�łȂ���΁A��O���X���[����B 
                //�@@�@@���`�F�b�N�Ώۍ���(*1)�́y�����i�L���R�[�h�A�Ӗ��j�z�Q�ƁB 
                //�@@class: WEB3BusinessLayerException
                //�@@tag: BUSINESS_ERROR_01311
                //
                //�@@�@@�I�u�W�F�N�g�������ł����ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^������Ɣ��肵�A 
                //�@@�@@�e��A�����ڑ���.validate�L���R�[�h�l()���R�[������B 
                //
                //�@@�@@[validate�L���R�[�h�l()�Ɏw�肷�����] 
                //�@@�@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l
                if (l_property != null)
                {
                    if (l_obj instanceof String)
                    {
                        boolean l_blnEffectiveValue;
                        l_blnEffectiveValue = l_property.validateEffectiveCodeValue(String.valueOf(l_obj));

                        if (!l_blnEffectiveValue)
                        {
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01311,
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_loopSpec.getColumnNameKana());
                        }

                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01311,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_loopSpec.getColumnNameKana());

                    }
                }                        
            }

            //�@@���@@�����`�F�b�N�̏ꍇ�ielse if(�e��A�����ڃ}�X�^.is�����`�F�b�N() == true)�j
            //    this.validate����()���R�[������B
            //�@@�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B 
            //�@@class: WEB3BusinessLayerException
            //�@@tag: BUSINESS_ERROR_01869
            //
            //�@@�@@[validate����()�Ɏw�肷�����] 
            //    �،���ЁF this.get�،����()�̖߂�l
            //    �����R�[�h�F �`�F�b�N�Ώۍ���(*1)�̒l
            else if (l_informItemMaster.isProductCheck())
            {
                Boolean l_blnValidateProduct = 
                    this.validateProduct(this.getInstitution(),String.valueOf(l_obj));
                if (Boolean.FALSE.equals(l_blnValidateProduct))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01869,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_loopSpec.getColumnNameKana());
                }
     
            }

            //���@@���M�����`�F�b�N�̏ꍇ�ielse if(�e��A�����ڃ}�X�^.is���M�����`�F�b�N() == true)�j
            //this.validate���M����()���R�[������B
            //false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B
            //[validate���M����()�Ɏw�肷�����]
            //�،���ЁF this.get�،����()�̖߂�l
            //�����R�[�h�F �`�F�b�N�Ώۍ���(*1)�̒l
            else if (l_informItemMaster.isMutualProductCheck())
            {
                boolean l_blnValidateMutualProduct =
                    this.validateMutualProduct(this.getInstitution(), String.valueOf(l_obj));

                if (!l_blnValidateMutualProduct)
                {
                    log.debug("���M�������擾�ł��܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02796,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_loopSpec.getColumnNameKana());
                }
            }

            //�@@���@@�������`�F�b�N�̏ꍇ�ielse if(�e��A�����ڃ}�X�^.is�������`�F�b�N() == true)�j
            //this.validate������()���R�[������B
            //false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B
            //[validate������()�Ɏw�肷�����]
            //�،���ЁF this.get�،����()�̖߂�l
            //�����R�[�h�F �`�F�b�N�Ώۍ���(*1)�̒l
            else if (l_informItemMaster.isBondProductCheck())
            {
                boolean l_blnValidateBondProduct =
                    this.validateBondProduct(this.getInstitution(), String.valueOf(l_obj));

                if (!l_blnValidateBondProduct)
                {
                    log.debug("���������擾�ł��܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02797,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_loopSpec.getColumnNameKana());
                }
            }

            //�@@���@@��L�`�F�b�N�ȊO�ielse�j�̏ꍇ 
            //�@@�@@�P�j�Ő��������e��A�����ڃ}�X�^.validate�L���l()���R�[������B 
            //�@@�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B 
            //�@@class: WEB3BusinessLayerException
            //�@@tag: BUSINESS_ERROR_01312
            //
            //�@@�@@[validate�L���l()�Ɏw�肷�����] 
            //�@@�@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l 
            else
            {
                if (!l_informItemMaster.validateEffectiveValue(variousInformParams.getColumn(l_loopSpec.asHeader())))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01312,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_loopSpec.getColumnNameKana());
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get�A�����)<BR>
     * �A����ʂ��擾����B<BR>
     * <BR>
     * this.�e��A���s.�A����ʂ�ԋp����B<BR>
     * @@return String
     * @@roseuid 41BD3DB50351
     */
    public String getInformDiv()
    {
        final String STR_METHOD_NAME = "getInformDiv()";
        log.entering(STR_METHOD_NAME);
        String l_strInfromDiv = this.variousInformParams.getInformDiv();
        log.exiting(STR_METHOD_NAME);
        return l_strInfromDiv;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * <BR>
     * this.�e��A���s.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41BD3E340082
     */
    public String getInstitutionCode()
    {
        final String STR_METHOD_NAME = "getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        String l_strInstitutionCode = this.variousInformParams.getInstitutionCode();
        log.exiting(STR_METHOD_NAME);
        return l_strInstitutionCode;
    }

    /**
     * (get�،����)<BR>
     * �e��A���s�̏�񂩂�،���ЃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h�F this.�e��A���s.�،���ЃR�[�h<BR>
     * ���X�R�[�h�F this.�e��A���s.���X�R�[�h<BR>
     * �����R�[�h�F this.�e��A���s.�ڋq�R�[�h<BR>
     * <BR>
     * �Q�j�ڋq.getInstitution()�̖߂�l��ԋp����B<BR>
     * @@return Institution
     * @@roseuid 41BD493201DA
     */
    public Institution getInstitution() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInstitution()";
        log.entering(STR_METHOD_NAME);
        Institution l_institution = null;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = 
            l_gentradeAccountManager.getMainAccount(this.variousInformParams.getInstitutionCode(),
            this.variousInformParams.getBranchCode(),
            this.variousInformParams.getAccountCode());
        l_institution = l_mainAccount.getInstitution();

        log.exiting(STR_METHOD_NAME);
        return l_institution;
    }

    /**
     * (get���X�R�[�h)<BR>
     * ���X�R�[�h���擾����B<BR>
     * <BR>
     * this.�e��A���s.���X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41BD3E5D016C
     */
    public String getBranchCode()
    {
        final String STR_METHOD_NAME = "getBranchCode()";
        log.entering(STR_METHOD_NAME);
        String l_strBranchCode = this.variousInformParams.getBranchCode();
        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }



    /**
     * (validate����)<BR>
     * �����̑��݃`�F�b�N���s���B<BR>
     * <BR>
     * �P�jEqType�̃g���[�f�B���O���W���[�����擾����B<BR>
     * <BR>
     * �Q�j�v���_�N�g�}�l�[�W�����擾����B<BR>
     * <BR>
     * �R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЁF ����.�،����<BR>
     * �����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �S�j�����I�u�W�F�N�g���擾�ł����ꍇ��true�A�擾�ł��Ȃ������ꍇ��false��ԋp����B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * 
     * @@return Boolean
     * @@roseuid 41BD44340322
     */
    protected Boolean validateProduct(
        Institution l_institution,
        String l_strProductCode)
    {
        final String STR_METHOD_NAME = "validateProduct";
        log.entering(STR_METHOD_NAME);
        boolean l_blnValidateProduct;
        //�P�jEqType�̃g���[�f�B���O���W���[�����擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�Q�j�v���_�N�g�}�l�[�W�����擾����B
        //
        EqTypeProductManager l_productManager = (EqTypeProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        //�R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B
        //
        //[����]
        //�،���ЁF ����.�،����
        //�����R�[�h�F ����.�����R�[�h
        //
        //�S�j�����I�u�W�F�N�g���擾�ł����ꍇ��true�A�擾�ł��Ȃ������ꍇ��false��ԋp����B
        try
        {
            l_productManager.getProduct(l_institution,l_strProductCode);
            l_blnValidateProduct = true;
        }
        catch(NotFoundException l_ex)
        {
            log.debug("NotFound Product.",l_ex);
            l_blnValidateProduct = false;
        }
        log.exiting(STR_METHOD_NAME);
        return new Boolean(l_blnValidateProduct);
    }


    /**
     * (saveNew�e��A��)<BR>
     * �e��A���e�[�u���Ƀf�[�^��o�^����B<BR> 
     * <BR>
     * �P�j �e��A���s�I�u�W�F�N�g�擾 <BR>
     * �@@this.�e��A���s���擾����B <BR>
     * <BR>
     * �Q�j �X�V�����Z�b�g����B <BR>
     * �@@�e��A���s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l�u�e��A��DB�X�V�d�l.xls�v�Q�� <BR>
     * <BR>
     * �R�j DB�o�^ <BR>
     * �@@�e��A���s�I�u�W�F�N�g�̓��e�ŁA�e��A���e�[�u���ɓo�^�iinsert�j����B <BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * 
     * @@param l_strRequstNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@roseuid 41BD5905037F
     */
    public void saveNewInform(
        String l_strUpdaterCode,
        String l_strRequstNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewInform(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j �e��A���s�I�u�W�F�N�g�擾 
        //�@@this.�e��A���s���擾����B 
        VariousInformParams l_params = this.variousInformParams;

        //�Q�j �X�V�����Z�b�g����B
        //�@@�e��A���s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B
        //
        //�@@DB�X�V�d�l�u�e��A��DB�X�V�d�l.xls�v�Q��
        if (l_params.getAccountCode() != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getAccountCode());
            //�ڋq�R�[�h
            l_params.setAccountCode(l_mainAccount.getAccountCode());

        }
        //���ʃR�[�h   request_number     ���ʃR�[�h�i��get�V�K���ʃR�[�h()�ɂĎ擾�������ʃR�[�h�j
        l_params.setRequestNumber(l_strRequstNumber);
        //���҃R�[�h   trader_code     �X�V�҃R�[�h
        l_params.setTraderCode(l_strUpdaterCode);                                                            
        //�X�V�҃R�[�h  last_updater     �X�V�҃R�[�h
        l_params.setLastUpdater(l_strUpdaterCode);                               
        //�쐬����    created_timestamp   ��������
        Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
        l_params.setCreatedTimestamp(l_tsProcessDate);     
        //�X�V����    last_updated_timestamp   ��������
        l_params.setLastUpdatedTimestamp(l_tsProcessDate);     

        try
        { 
            //�R�j DB�o�^ 
            //�@@�e��A���s�I�u�W�F�N�g�̓��e�ŁA�e��A���e�[�u���ɓo�^�iinsert�j����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                
            l_queryProcessor.doInsertQuery(l_params);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�e��A���s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 41BD32D201AB
     */
    public Object getDataSourceObject()
    {
        final String STR_METHOD_NAME = "getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.variousInformParams;
    }
    /**
     * (get�t�����)<BR>
     * ���X�|���X�ɃZ�b�g����t�������擾����B<BR>
     * <BR>
     * �P�j�e��A���t�����̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�jthis.�e��A���s�̃R�[�h�P�`�P�O�̍��ڂɂ��āA�ȉ��̏������s���B <BR>
     * <BR>
     * �Q�|�P�j�@@�e��A�����ڃ}�X�^���� <BR>
     * �@@�e��A�����ڃ}�X�^�𐶐�����B <BR>
     * �@@�����@@��null���ԋp���ꂽ�ꍇ�́A�����A�Ő�������B <BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈����@@] <BR>
     * �@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@���X�R�[�h�F�@@this.get���X�R�[�h()�̖߂�l<BR>
     * �@@�A����ʁF�@@this.get�A�����()�̖߂�l<BR>
     * �@@���ڕ������F�@@�Ώۍ���(*1) <BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈����A] <BR>
     * �@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@���X�R�[�h�F�@@�h000�h <BR>
     * �@@�A����ʁF�@@this.get�A�����()�̖߂�l<BR>
     * �@@���ڕ������F�@@�Ώۍ���(*1) <BR>
     * <BR>
     * �@@�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ��Ɣ��f���A <BR>
     * �@@�e��A�����ڃ}�X�^.getDefault���ڃ}�X�^()�ɂăC���X�^���X�𐶐�����B<BR> 
     * <BR>
     * �@@[getDefault���ڃ}�X�^()�Ɏw�肷�����] <BR>
     * �@@�K�{���ڃt���O�F�@@�Ώۍ���(*1)���yNull�z���ڂł����BooleanEnum.FALSE�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�yNotNull�z���ڂł���΁ABooleanEnum.TRUE���w�肷��B <BR>
     * �@@���ڍő咷�F�@@�Ώۍ���(*1)�́ySIZE�z <BR>
     * �@@���ڃ`�F�b�N�����F�@@�Ώۍ���(*1)�́y���ڃ`�F�b�N�����iWEB3�f�t�H���g�j�z <BR>
     * <BR>
     * �Q�|�Q�j�Ώۍ��ڂ̃`�F�b�N�����������`�F�b�N or ���M�����`�F�b�N or �������`�F�b�N<BR>
     * �̏ꍇ�A�i�e��A�����ڃ}�X�^.is�����`�F�b�N() == true or<BR>
     * is���M�����`�F�b�N() == true or is�������`�F�b�N() == true�j<BR>
     * <BR>
     *   �Q�|�Q�|�P�jEqType�̃g���[�f�B���O���W���[�����擾����B<BR>
     * <BR>
     *   �Q�|�Q�|�Q�j�v���_�N�g�}�l�[�W�����擾����B<BR>
     * <BR>
     *   �Q�|�Q�|�R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B<BR>
     * <BR>
     * �@@�@@[get����()�Ɏw�肷�����] <BR>
     *     �،���ЁF this.get�،����()�̖߂�l<BR>
     *     �����R�[�h�F �Ώۍ���(*1)�̒l<BR>
     * <BR>
     * �Q�|�R�j�����I�u�W�F�N�g���擾�����ꍇ<BR>
     *   �R�[�h���ڂ̘A�ԂɑΉ�����e��A���t�����̕t����񍀖ڂ�<BR>
     *   ���������Z�b�g����B<BR>
     * <BR>
     * �@@�Q�|�Q�|�Q �j�@@���M�����`�F�b�N�̏ꍇ�A���M�����I�u�W�F�N�g���擾����B<BR>
     *<BR>
     * �@@�@@�Q�|�Q�|�Q�|�P�j���M�̃g���[�f�B���O���W���[�����擾����B<BR>
     *<BR>
     * �@@  �Q�|�Q�|�Q�|�Q�j�v���_�N�g�}�l�[�W�����擾����B<BR>
     *<BR>
     * �@@  �Q�|�Q�|�Q�|�R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B<BR>
     *<BR>
     * �@@�@@�Q�|�Q�|�Q�|�S�j���M�����I�u�W�F�N�g���擾�����ꍇ<BR>
     *   �@@�R�[�h���ڂ̘A�ԂɑΉ�����e��A���t�����̕t����񍀖ڂ�<BR>
     * �@@  ���������Z�b�g����B<BR>
     *<BR>
     * �@@�@@[get����()�Ɏw�肷�����] <BR>
     *     �،���ЁF this.get�،����()�̖߂�l<BR>
     *     �����R�[�h�F �Ώۍ���(*1)�̒l<BR>
     *<BR>
     * �@@�Q�|�Q�|�R �j�@@�������`�F�b�N�̏ꍇ�A�������I�u�W�F�N�g���擾����B<BR>
     *<BR>
     * �@@�@@�Q�|�Q�|�R�|�P�j���̃g���[�f�B���O���W���[�����擾����B<BR>
     *<BR>
     * �@@  �Q�|�Q�|�R�|�Q�j�v���_�N�g�}�l�[�W�����擾����B<BR>
     *<BR>
     * �@@  �Q�|�Q�|�R�|�R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B<BR>
     *<BR>
     * �@@�@@�Q�|�Q�|�R�|�S�j�������I�u�W�F�N�g���擾�����ꍇ<BR>
     *   �@@�R�[�h���ڂ̘A�ԂɑΉ�����e��A���t�����̕t����񍀖ڂ�<BR>
     * �@@  ���������Z�b�g����B<BR>
     *<BR>
     * �@@�@@[get����()�Ɏw�肷�����] <BR>
     *     �،���ЁF this.get�،����()�̖߂�l<BR>
     *     �����R�[�h�F �Ώۍ���(*1)�̒l<BR>
     *<BR>
     * �R�j�������ꂽ�e��A���t������ԋp����B<BR>
     * @@return WEB3InformAddInfoUnit
     */
    public WEB3InformAddInfoUnit getInformAddInfoUnit() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformAddInfoUnit()";
        log.entering(STR_METHOD_NAME);
        //�P�j�e��A���t�����̃C���X�^���X�𐶐�����B
        WEB3InformAddInfoUnit l_informAddInforUnit = new WEB3InformAddInfoUnit();
        //�Q�jthis.�e��A���s�̃R�[�h�P�`�P�O�̍��ڂɂ��āA�ȉ��̏������s���B 
        //
        //�Q�|�P�j�@@�e��A�����ڃ}�X�^���� 
        //�@@�e��A�����ڃ}�X�^�𐶐�����B 
        //�@@�����@@��null���ԋp���ꂽ�ꍇ�́A�����A�Ő�������B 
        //
        //�@@[�R���X�g���N�^�̈����@@] 
        //�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l
        //�@@���X�R�[�h�F�@@this.get���X�R�[�h()�̖߂�l
        //�@@�A����ʁF�@@this.get�A�����()�̖߂�l
        //�@@���ڕ������F�@@�Ώۍ���(*1) 
        //
        //�@@[�R���X�g���N�^�̈����A] 
        //�@@�،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()�̖߂�l
        //�@@���X�R�[�h�F�@@�h000�h 
        //�@@�A����ʁF�@@this.get�A�����()�̖߂�l
        //�@@���ڕ������F�@@�Ώۍ���(*1) 
        //
        WEB3InformItemMaster l_informItemMaster = null;
        WEB3InformTableSpec l_spec = new WEB3InformTableSpec();


        String[] l_strItemSymbolNames = {"ext_code1","ext_code2","ext_code3","ext_code4","ext_code5","ext_code6",
                "ext_code7","ext_code8","ext_code9","ext_code10"};
        for (int i = 0; i < 10;i++)
        {
            WEB3InformColumnSpec l_loopSpec = l_spec.getColumnSpec(VariousInformRow.TYPE.getTableName(),l_strItemSymbolNames[i]);
            try
            {
                l_informItemMaster = new WEB3InformItemMaster(
                    this.getInstitutionCode(),
                    this.getBranchCode(),
                    this.getInformDiv(),
                    l_strItemSymbolNames[i]); 
            }
            catch (NotFoundException l_ex)
            {
                try
                {
                    l_informItemMaster = new WEB3InformItemMaster(
                        this.getInstitutionCode(),
                        WEB3BranchCodeDef.DEFAULT,
                        this.getInformDiv(),
                        l_strItemSymbolNames[i]); 
                }
                catch (NotFoundException l_ex1)
                {
                    //�@@�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ��Ɣ��f���A 
                    //�@@�e��A�����ڃ}�X�^.getDefault���ڃ}�X�^()�ɂăC���X�^���X�𐶐�����B 
                    //
                    //�@@[getDefault���ڃ}�X�^()�Ɏw�肷�����] 
                    //�@@�K�{���ڃt���O�F�@@�Ώۍ���(*1)���yNull�z���ڂł����BooleanEnum.FALSE�A 
                    //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�yNotNull�z���ڂł���΁ABooleanEnum.TRUE���w�肷��B 
                    //�@@���ڍő咷�F�@@�Ώۍ���(*1)�́ySIZE�z 
                    //�@@���ڃ`�F�b�N�����F�@@�Ώۍ���(*1)�́y���ڃ`�F�b�N�����iWEB3�f�t�H���g�j�z 
                    //
                    BooleanEnum l_boolEnum = null;
                    if (l_loopSpec.getIsNullable())
                    {
                        l_boolEnum = BooleanEnum.FALSE;
                    }
                    else
                    {
                        l_boolEnum = BooleanEnum.TRUE;
                    }
                    l_informItemMaster = WEB3InformItemMaster.getDefaultItemMaster(l_boolEnum,
                        l_loopSpec.columnSize(),l_loopSpec.getDefaultCheckMode());

                }
            }
                       
            //�Q�|�Q�j�Ώۍ��ڂ̃`�F�b�N�����������`�F�b�N�̏ꍇ�i�e��A�����ڃ}�X�^.is�����`�F�b�N() == true�j
            //  �����I�u�W�F�N�g���擾����B
            //
            Product l_product = null;
            String l_strProductName = null;            
            
            if (l_informItemMaster.isProductCheck())
            {
                //  �Q�|�Q�|�P�jEqType�̃g���[�f�B���O���W���[�����擾����B
                //
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                //  �Q�|�Q�|�Q�j�v���_�N�g�}�l�[�W�����擾����B
                //
                EqTypeProductManager l_productManager = (EqTypeProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                //  �Q�|�Q�|�R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B
                //
                //�@@�@@[get����()�Ɏw�肷�����] 
                //    �،���ЁF this.get�،����()�̖߂�l
                //    �����R�[�h�F �Ώۍ���(*1)�̒l
                //
                
                String l_strValue = null;
                if (this.variousInformParams.getColumn(l_strItemSymbolNames[i]) == null)
                {
                    continue;
                }
                l_strValue = this.variousInformParams.getColumn(l_strItemSymbolNames[i]).toString();
                try
                {
                    l_product = l_productManager.getProduct(this.getInstitution(),l_strValue);
                    l_strProductName = l_product.getStandardName();
                }
                catch (NotFoundException l_ex)
                {
                    log.error(STR_METHOD_NAME,l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }


                //�Q�|�R�j�����I�u�W�F�N�g���擾�����ꍇ
                //  �R�[�h���ڂ̘A�ԂɑΉ�����e��A���t�����̕t����񍀖ڂ�
                //  ���������Z�b�g����B
                //
                switch (i)
                {
                    case 0:l_informAddInforUnit.addInfo1 = l_strProductName;break;
                    case 1:l_informAddInforUnit.addInfo2 = l_strProductName;break;
                    case 2:l_informAddInforUnit.addInfo3 = l_strProductName;break;
                    case 3:l_informAddInforUnit.addInfo4 = l_strProductName;break;
                    case 4:l_informAddInforUnit.addInfo5 = l_strProductName;break;
                    case 5:l_informAddInforUnit.addInfo6 = l_strProductName;break;
                    case 6:l_informAddInforUnit.addInfo7 = l_strProductName;break;
                    case 7:l_informAddInforUnit.addInfo8 = l_strProductName;break;
                    case 8:l_informAddInforUnit.addInfo9 = l_strProductName;break;
                    default: l_informAddInforUnit.addInfo10 = l_strProductName;
                }
            }

            if (l_informItemMaster.isMutualProductCheck())
            {
                //�Q�|�Q�|�Q�|�P�j���M�̃g���[�f�B���O���W���[�����擾����B
                FinApp l_finapp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

                //�Q�|�Q�|�Q�|�Q�j�v���_�N�g�}�l�[�W�����擾����B
                WEB3MutualFundProductManager l_mutualFundProductManager =
                    (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

                // �Q�|�Q�|�Q�|�R�j�v���_�N�g�}�l�[�W��.get����()���R�[������
                String l_strValue = null;
                if (this.variousInformParams.getColumn(l_strItemSymbolNames[i]) == null)
                {
                    continue;
                }
                l_strValue = this.variousInformParams.getColumn(l_strItemSymbolNames[i]).toString();

                try
                {
                    l_product = l_mutualFundProductManager.getMutualFundProduct(this.getInstitution(), l_strValue);
                    l_strProductName = l_product.getStandardName();
                }
                catch (NotFoundException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�@@�@@�Q�|�Q�|�Q�|�S�j���M�����I�u�W�F�N�g���擾�����ꍇ
                //�R�[�h���ڂ̘A�ԂɑΉ�����e��A���t�����̕t����񍀖ڂ�
                //���������Z�b�g����B
                switch (i)
                {
                    case 0:l_informAddInforUnit.addInfo1 = l_strProductName; break;
                    case 1:l_informAddInforUnit.addInfo2 = l_strProductName; break;
                    case 2:l_informAddInforUnit.addInfo3 = l_strProductName; break;
                    case 3:l_informAddInforUnit.addInfo4 = l_strProductName; break;
                    case 4:l_informAddInforUnit.addInfo5 = l_strProductName; break;
                    case 5:l_informAddInforUnit.addInfo6 = l_strProductName; break;
                    case 6:l_informAddInforUnit.addInfo7 = l_strProductName; break;
                    case 7:l_informAddInforUnit.addInfo8 = l_strProductName; break;
                    case 8:l_informAddInforUnit.addInfo9 = l_strProductName; break;
                    default: l_informAddInforUnit.addInfo10 = l_strProductName;
                }
            }

            if (l_informItemMaster.isBondProductCheck())
            {
                //�Q�|�Q�|�R�|�P�j���̃g���[�f�B���O���W���[�����擾����B
                FinApp l_finapp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.BOND);

                //�Q�|�Q�|�R�|�Q�j�v���_�N�g�}�l�[�W�����擾����B
                WEB3BondProductManager l_bondProductManager =
                    (WEB3BondProductManager)l_tradingModule.getProductManager();

                // �Q�|�Q�|�R�|�R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B
                String l_strValue = null;
                if (this.variousInformParams.getColumn(l_strItemSymbolNames[i]) == null)
                {
                    continue;
                }
                l_strValue = this.variousInformParams.getColumn(l_strItemSymbolNames[i]).toString();

                try
                {
                    l_product = l_bondProductManager.getBondProduct(this.getInstitution(), l_strValue);
                    l_strProductName = l_product.getStandardName();
                }
                catch (NotFoundException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�@@�@@�Q�|�Q�|�R�|�S�j�������I�u�W�F�N�g���擾�����ꍇ
                //�R�[�h���ڂ̘A�ԂɑΉ�����e��A���t�����̕t����񍀖ڂ�
                //���������Z�b�g����B
                switch (i)
                {
                    case 0:l_informAddInforUnit.addInfo1 = l_strProductName; break;
                    case 1:l_informAddInforUnit.addInfo2 = l_strProductName; break;
                    case 2:l_informAddInforUnit.addInfo3 = l_strProductName; break;
                    case 3:l_informAddInforUnit.addInfo4 = l_strProductName; break;
                    case 4:l_informAddInforUnit.addInfo5 = l_strProductName; break;
                    case 5:l_informAddInforUnit.addInfo6 = l_strProductName; break;
                    case 6:l_informAddInforUnit.addInfo7 = l_strProductName; break;
                    case 7:l_informAddInforUnit.addInfo8 = l_strProductName; break;
                    case 8:l_informAddInforUnit.addInfo9 = l_strProductName; break;
                    default: l_informAddInforUnit.addInfo10 = l_strProductName;
                }
            }
        }

        //�R�j�������ꂽ�e��A���t������ԋp����B
        
        log.exiting(STR_METHOD_NAME);
        return l_informAddInforUnit;
    }

    /**
     * (validate���M����)<BR>
     * ���M�����̑��݃`�F�b�N���s���B<BR>
     *<BR>
     * �P�j���M�̃g���[�f�B���O���W���[�����擾����B<BR>
     *<BR>
     * �Q�j�v���_�N�g�}�l�[�W�����擾����B<BR>
     *<BR>
     * �R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B<BR>
     *<BR>
     * [����]<BR>
     * �،���ЁF ����.�،����<BR>
     * �����R�[�h�F ����.�����R�[�h<BR>
     *<BR>
     * �S�j�����I�u�W�F�N�g���擾�ł����ꍇ��true�A�擾�ł��Ȃ������ꍇ��false��ԋp����B<BR>
     * @@param l_institution - �،����
     * @@param l_strProductCode - �����R�[�h
     * @@return boolean
     */
    protected boolean validateMutualProduct(Institution l_institution, String l_strProductCode)
    {
        final String STR_METHOD_NAME = "validateMutualProduct(Institution, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j���M�̃g���[�f�B���O���W���[�����擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        //�Q�j�v���_�N�g�}�l�[�W�����擾����B
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        //�R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B
        try
        {
            l_mutualFundProductManager.getMutualFundProduct(l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate������)<BR>
     * �������̑��݃`�F�b�N���s���B<BR>
     *<BR>
     * �P�j���̃g���[�f�B���O���W���[�����擾����B<BR>
     *<BR>
     * �Q�j�v���_�N�g�}�l�[�W�����擾����B<BR>
     *<BR>
     * �R�j�v���_�N�g�}�l�[�W��.get����()���R�[������B<BR>
     *<BR>
     * [����]<BR>
     * �،���ЁF ����.�،����<BR>
     * �����R�[�h�F ����.�����R�[�h<BR>
     *<BR>
     * �S�j�����I�u�W�F�N�g���擾�ł����ꍇ��true�A�擾�ł��Ȃ������ꍇ��false��ԋp����B<BR>
     * @@param l_institution - �،����
     * @@param l_strProductCode - �����R�[�h
     * @@return boolean
     */
    protected boolean validateBondProduct(Institution l_institution, String l_strProductCode)
    {
        final String STR_METHOD_NAME = "validateBondProduct(Institution, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j���̃g���[�f�B���O���W���[�����擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);

        //�Q�j�v���_�N�g�}�l�[�W�����擾����B
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();

        //�v���_�N�g�}�l�[�W��.get����()���R�[������B
        try
        {
            l_bondProductManager.getBondProduct(l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get�e��A���s)<BR>
     * ��L�[����Ɋe��A���e�[�u������������B<BR>
     *<BR>
     * �P�j�@@�e��A���e�[�u���̌���<BR>
     * [��������]<BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * �A����ʁF ����.�A�����<BR>
     * ���ʃR�[�h�F ����.���ʃR�[�h<BR>
     * ���X�R�[�h�F ����.���X�R�[�h<BR>
     *<BR>
     * * ���R�[�h��������Ȃ��ꍇ�Anull��ԋp<BR>
     *<BR>
     * �Q�j�������ʂ�ԋp<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_strInformType - �A�����
     * @@throws WEB3BaseException
     * @@return VariousInformParams - �e��A��Params
     */
    public static VariousInformParams getVariousInform(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber,
        String l_strInformType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getVariousInform(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        VariousInformPK l_variousInformPK = new VariousInformPK();
        l_variousInformPK.institution_code = l_strInstitutionCode;
        l_variousInformPK.branch_code = l_strBranchCode;
        l_variousInformPK.request_number = l_strRequestNumber;
        l_variousInformPK.inform_div = l_strInformType;

        VariousInformParams l_variousInformParams = null;
        try
        {
            //�P�j�@@�e��A���e�[�u���̌���
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_variousInformParams =
                (VariousInformParams)l_queryProcessor.doFindByPrimaryKeyQuery(l_variousInformPK);
        }
        catch (DataFindException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_variousInformParams;
    }

    /**
     * (update�e��A��)
     * �e��A���e�[�u���̃��R�[�h���X�V����B<BR>
     *<BR>
     * �m�X�V�����n<BR>
     * �،���ЃR�[�h = �e��A���s.get�،���ЃR�[�h�i�j and<BR>
     * �A����� = �e��A���s.get�A����ʁi�j and<BR>
     * ���ʃR�[�h = �e��A���s.get���ʃR�[�h�i�j and<BR>
     * ���X�R�[�h = �e��A���s.get���X�R�[�h�i�j<BR>
     * @@param l_map - Map
     * @@throws WEB3BaseException
     */
    public void updateInform(Map l_map) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateInform(Map)";
        log.entering(STR_METHOD_NAME);

        String l_strUpdateWhere = " institution_code = ? "
            + " and inform_div = ? "
            + " and request_number = ? "
            + " and branch_code = ? ";

        Object[] l_arrayUpdateParams = {
            this.variousInformParams.getInstitutionCode(),
            this.variousInformParams.getInformDiv(),
            this.variousInformParams.getRequestNumber(),
            this.variousInformParams.getBranchCode()
        };

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                VariousInformRow.TYPE,
                l_strUpdateWhere,
                l_arrayUpdateParams,
                l_map);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveNew�e��A��)<BR>
     * �e��A���e�[�u���Ƀf�[�^��o�^����B<BR>
     *<BR>
     * �P�j �e��A���s�I�u�W�F�N�g�擾<BR>
     * �@@this.�e��A���s���擾����B<BR>
     *<BR>
     * �Q�j �X�V�����Z�b�g����B<BR>
     * �@@�e��A���s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B<BR>
     *<BR>
     * �@@DB�X�V�d�l�u���z���U�֌����o�^_�e��A���e�[�u��.xls�v�Q��<BR>
     *<BR>
     * �R�j DB�o�^<BR>
     * �@@�e��A���s�I�u�W�F�N�g�̓��e�ŁA�e��A���e�[�u���ɓo�^�iinsert�j����B<BR>
     *@@param l_strUpdaterCode - �X�V�҃R�[�h
     *@@param l_strRequestNumber - ���ʃR�[�h
     *@@param l_strMakeStatus - �쐬��
     *@@throws WEB3BaseException
     */
    public void saveNewInform(
        String l_strUpdaterCode,
        String l_strRequestNumber,
        String l_strMakeStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewVariousInform(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j �e��A���s�I�u�W�F�N�g�擾
        //�@@this.�e��A���s���擾����B
        VariousInformParams l_params = this.variousInformParams;

        //�Q�j �X�V�����Z�b�g����B
        //�e��A���s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B
        //DB�X�V�d�l�u���z���U�֌����o�^_�e��A���e�[�u��.xls�v�Q��
        //���ʃR�[�h  request_number  ���ʃR�[�h�i��get�V�K���ʃR�[�h()�ɂĎ擾�������ʃR�[�h�j
        l_params.setRequestNumber(l_strRequestNumber);

        // �����ԍ�
        if (l_params.getAccountCode() != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getAccountCode());
            //�ڋq�R�[�h
            l_params.setAccountCode(l_mainAccount.getAccountCode());
        }

        //���҃R�[�h  trader_code  null
        l_params.setTraderCode(null);

        //�X�V�҃R�[�h  last_updater  ����:�X�V�҃R�[�h
        l_params.setLastUpdater(l_strUpdaterCode);

        //�쐬����  created_timestamp  ��������
        Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
        l_params.setCreatedTimestamp(l_tsProcessDate);

        //�X�V����  last_updated_timestamp  ��������
        l_params.setLastUpdatedTimestamp(l_tsProcessDate);

        //�`�[�쐬��  status  ����:�쐬��
        l_params.setStatus(l_strMakeStatus);

        //�G���[���R�R�[�h  error_reason_code  null
        l_params.setErrorReasonCode(null);

        //�`�[���ʃR�[�h  order_request_number  null
        l_params.setOrderRequestNumber(null);

        //�f�[�^�R�[�h  request_code  null
        l_params.setRequestCode(null);

        //�`�[���M����  send_timestamp null
        l_params.setSendTimestamp(null);

        //�`�[��M����  receipt_timestamp  null
        l_params.setReceiptTimestamp(null);

        try
        {
            //�R�j DB�o�^
            //�@@�e��A���s�I�u�W�F�N�g�̓��e�ŁA�e��A���e�[�u���ɓo�^�iinsert�j����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_params);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�e��A���s�Ɉ���:�e��A���s���Z�b�g����B<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s
     */
    public WEB3Inform(VariousInformParams l_variousInformParams)
    {
        this.variousInformParams = l_variousInformParams;
    }

    /**
     * (get�e��A��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �ڋq�ɊY������e��A�����擾����B<BR>
     * <BR>
     * �P�j�@@�ڋq.getDataSourceObject()�ɂČڋq�s���擾����B<BR>
     * <BR>
     * �Q�j�@@�e��A���e�[�u������<BR>
     * �@@�ȉ��̏����ŁA�e��A���e�[�u������������B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h�F �ڋq�s.get�،���ЃR�[�h()<BR>
     * �@@�@@�A����ʁF ����.�A�����<BR>
     * �@@�@@���X�R�[�h�F �ڋq�s.get���X�R�[�h()<BR>
     * �@@�@@�ڋq�R�[�h�F �ڋq�s.get�����R�[�h()<BR>
     * �@@�@@�敪�P�F  "1"�i�L���j<BR>
     * �@@[�\�[�g����]<BR>
     * �@@�@@�X�V�����i�~���j<BR>
     * �@@�@@�@@���������擾�����ꍇ�́A�擪���R�[�h�i�X�V�������V�������́j�Ƃ���B<BR>
     * <BR>
     * �R�j�@@�e��A���I�u�W�F�N�g�ԋp<BR>
     * �@@�擾�����s�I�u�W�F�N�g�ɂ��āA�e��A���I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * �@@�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strInformDiv - (�A�����)<BR>
     * �A�����<BR>
     * @@return WEB3Inform
     * @@throws WEB3BaseException
     */
    public static WEB3Inform getVariousInform(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strInformDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getVariousInform(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        //�ڋq.getDataSourceObject()�ɂČڋq�s���擾����
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�e��A���e�[�u������
        // �@@[��������]
        // �@@�@@�،���ЃR�[�h�F �ڋq�s.get�،���ЃR�[�h()
        // �@@�@@�A����ʁF ����.�A�����
        // �@@�@@���X�R�[�h�F �ڋq�s.get���X�R�[�h()
        // �@@�@@�ڋq�R�[�h�F �ڋq�s.get�����R�[�h()
        // �@@�@@�敪�P�F  "1"�i�L���j
        // �@@[�\�[�g����]
        // �@@�@@�X�V�����i�~���j
        // �@@�@@�@@���������擾�����ꍇ�́A�擪���R�[�h�i�X�V�������V�������́j�Ƃ���B
        StringBuffer l_sbSql = new StringBuffer();
        l_sbSql.append(" institution_code = ? ");
        l_sbSql.append(" and inform_div = ? ");
        l_sbSql.append(" and branch_code = ? ");
        l_sbSql.append(" and account_code = ? ");
        l_sbSql.append(" and ext_div1 = ? ");

        Object[] l_sqlValues = new Object[]{
            l_mainAccountRow.getInstitutionCode(),
            l_strInformDiv,
            l_mainAccountRow.getBranchCode(),
            l_mainAccountRow.getAccountCode(),
            WEB3ExtDiv1Def.VALIDITY};

        String l_strSort = " last_updated_timestamp desc ";
        List l_lisResults = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_sbSql.toString(),
                l_strSort,
                null,
                l_sqlValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�e��A���I�u�W�F�N�g�ԋp
        //�@@�擾�����s�I�u�W�F�N�g�ɂ��āA�e��A���I�u�W�F�N�g�𐶐����ԋp����B
        //�@@�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
        if (l_lisResults.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        VariousInformParams l_variousInformParams =
            new VariousInformParams((VariousInformRow)l_lisResults.get(0));
        WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);

        log.exiting(STR_METHOD_NAME);
        return l_inform;
    }

    /**
     * (createNew�e��A��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �e��A���V�K�s�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g����<BR>
     * �@@�e��A��Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@���e��A��Params��DDL��莩����������<BR>
     * <BR>
     * �Q�j�@@�ڋq.getDataSourceObject()�ɂČڋq�s���擾����B<BR>
     * <BR>
     * �R�j�@@�I�u�W�F�N�g�̃v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B<BR>
     * �@@�P�j�Ő��������e��A��Params�I�u�W�F�N�g�̃v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B<BR>
     * <BR>
     * �@@�e��A���s.�،���ЃR�[�h �� �ڋq�s.�،���ЃR�[�h<BR>
     * �@@�e��A���s.�A����� �� ����.�A�����<BR>
     * �@@�e��A���s.���ʃR�[�h �� ����.���ʃR�[�h<BR>
     * �@@�e��A���s.���X�R�[�h �� �ڋq�s.���X�R�[�h<BR>
     * �@@�e��A���s.�ڋq�R�[�h �� �ڋq�s.�����R�[�h<BR>
     * �@@�e��A���s.�ڋq�� �� �ڋq�s.���O�i�c���j<BR>
     * �@@�e��A���s.�敪�P �� "1"(�L��)<BR>
     * �@@�e��A���s.�敪�Q �� ����.PTS�����J�݋敪<BR>
     * �@@�e��A���s.�X�V�҃R�[�h �� ����.�X�V�҃R�[�h<BR>
     * �@@�e��A���s.�쐬���� �� ��������<BR>
     * �@@�e��A���s.�X�V���� �� ��������<BR>
     * <BR>
     * �S�j�@@�e��A���I�u�W�F�N�g�ԋp<BR>
     * �@@�s�I�u�W�F�N�g���w�肵�A�e��A���I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strInformDiv - (�A�����)<BR>
     * �A�����<BR>
     * @@param l_strPTSAccOpenDiv - (PTS�����J�݋敪)<BR>
     * PTS�����J�݋敪<BR>
     * @@param l_strLastUpdater - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@return WEB3Inform
     */
    public static WEB3Inform createNewVariousInform(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strInformDiv,
        String l_strPTSAccOpenDiv,
        String l_strLastUpdater,
        String l_strRequestNumber)
    {
        final String STR_METHOD_NAME = "createNewVariousInform(" +
            "WEB3GentradeMainAccount, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�e��A��Params�I�u�W�F�N�g�𐶐�����
        VariousInformParams l_variousInformParams =
            new VariousInformParams();

        //�ڋq.getDataSourceObject()�ɂČڋq�s���擾����
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�I�u�W�F�N�g�̃v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s��
        //���������e��A��Params�I�u�W�F�N�g�̃v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s��
        //�@@�e��A���s.�،���ЃR�[�h �� �ڋq�s.�،���ЃR�[�h
        l_variousInformParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());

        //�@@�e��A���s.�A����� �� ����.�A�����
        l_variousInformParams.setInformDiv(l_strInformDiv);

        //�@@�e��A���s.���ʃR�[�h �� ����.���ʃR�[�h
        l_variousInformParams.setRequestNumber(l_strRequestNumber);

        //�@@�e��A���s.���X�R�[�h �� �ڋq�s.���X�R�[�h
        l_variousInformParams.setBranchCode(l_mainAccountRow.getBranchCode());

        //�@@�e��A���s.�ڋq�R�[�h �� �ڋq�s.�����R�[�h
        l_variousInformParams.setAccountCode(l_mainAccountRow.getAccountCode());

        //�@@�e��A���s.�ڋq�� �� �ڋq�s.���O�i�c���j
        l_variousInformParams.setAccountName(l_mainAccountRow.getFamilyName());

        //�@@�e��A���s.�敪�P �� "1"(�L��)
        l_variousInformParams.setExtDiv1(WEB3ExtDiv1Def.VALIDITY);

        //�@@�e��A���s.�敪�Q �� ����.PTS�����J�݋敪
        l_variousInformParams.setExtDiv2(l_strPTSAccOpenDiv);

        //�@@�e��A���s.�X�V�҃R�[�h �� ����.�X�V�҃R�[�h
        l_variousInformParams.setLastUpdater(l_strLastUpdater);

        //�@@�e��A���s.�쐬���� �� ��������
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_variousInformParams.setCreatedTimestamp(l_tsSystemTime);

        //�@@�e��A���s.�X�V���� �� ��������
        l_variousInformParams.setLastUpdatedTimestamp(l_tsSystemTime);

        //�e��A���I�u�W�F�N�g�ԋp
        //�s�I�u�W�F�N�g���w�肵�A�e��A���I�u�W�F�N�g�𐶐����ԋp����
        log.exiting(STR_METHOD_NAME);
        return new WEB3Inform(l_variousInformParams);
    }

    /**
     * (saveNew�e��A��)<BR>
     * �e��A���e�[�u���Ƀf�[�^��o�^����B<BR>
     * <BR>
     * �P�j �e��A���s�I�u�W�F�N�g�擾<BR>
     * �@@this.�e��A���s���擾����B<BR>
     * <BR>
     * �Q�j DB�o�^<BR>
     * �@@�e��A���s�I�u�W�F�N�g�̓��e�ŁA�e��A���e�[�u���ɓo�^�iinsert�j����B<BR>
     * <BR>
     * �@@DB�X�V�d�l�uPTS�����J�ݐ\��_�e��A���e�[�u��.xls�v<BR>
     * �@@PTS�����J�ݐ\��_insert_DB�X�V�d�l �Q��<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void saveNewVariousInform() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewVariousInform()";
        log.entering(STR_METHOD_NAME);

        //�e��A���s�I�u�W�F�N�g�擾
        //this.�e��A���s���擾����
        VariousInformParams l_variousInformParams = this.variousInformParams;

        //DB�o�^
        //�e��A���s�I�u�W�F�N�g�̓��e�ŁA�e��A���e�[�u���ɓo�^�iinsert�j����
        //DB�X�V�d�l�uPTS�����J�ݐ\��_�e��A���e�[�u��.xls�v
        //PTS�����J�ݐ\��_insert_DB�X�V�d�l �Q��
        //���҃R�[�h:null
        l_variousInformParams.setTraderCode(null);

        //�쐬����:��������
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_variousInformParams.setCreatedTimestamp(l_tsSystemTime);

        //�X�V����:��������
        l_variousInformParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_variousInformParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updatePTS�e��A��)<BR>
     * �e��A���e�[�u���̃��R�[�h���X�V����B<BR>
     * <BR>
     * DB�X�V�d�l�uPTS�����J�ݐ\��_�e��A���e�[�u��.xls�v<BR>
     * PTS�����J�ݐ\��_update_DB�X�V�d�l �Q��<BR>
     * <BR>
     * �m�X�V�����n<BR>
     * �@@�،���ЃR�[�h = �e��A���s.get�،���ЃR�[�h() and<BR>
     * �@@�A����� = �e��A���s.get�A�����() and<BR>
     * �@@���ʃR�[�h = �e��A���s.get���ʃR�[�h() and<BR>
     * �@@���X�R�[�h = �e��A���s.get���X�R�[�h()<BR>
     * <BR>
     * @@param l_updateContents - (�X�V���e)<BR>
     * �X�V���e<BR>
     * @@throws WEB3BaseException
     */
    public void updatePTSVariousInform(Map l_updateContents) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updatePTSVariousInform(Map)";
        log.entering(STR_METHOD_NAME);

        //�m�X�V�����n
        //�@@�،���ЃR�[�h = �e��A���s.get�،���ЃR�[�h() and
        //�@@�A����� = �e��A���s.get�A�����() and
        //�@@���ʃR�[�h = �e��A���s.get���ʃR�[�h() and
        //�@@���X�R�[�h = �e��A���s.get���X�R�[�h()
        StringBuffer l_sbUpdateSql = new StringBuffer();
        l_sbUpdateSql.append(" institution_code = ? ");
        l_sbUpdateSql.append(" and inform_div = ? ");
        l_sbUpdateSql.append(" and request_number = ? ");
        l_sbUpdateSql.append(" and branch_code = ? ");

        Object[] l_updateValues = new Object[]{
            this.variousInformParams.getInstitutionCode(),
            this.variousInformParams.getInformDiv(),
            this.variousInformParams.getRequestNumber(),
            this.variousInformParams.getBranchCode()};

        //DB�X�V�d�l�uPTS�����J�ݐ\��_�e��A���e�[�u��.xls�v
        //PTS�����J�ݐ\��_update_DB�X�V�d�l �Q��
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                VariousInformRow.TYPE,
                l_sbUpdateSql.toString(),
                l_updateValues,
                l_updateContents);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�敪�Q)<BR>
     * �敪�Q���擾����B<BR>
     * <BR>
     * this.�e��A���s.�敪�Q��ԋp����<BR>
     * <BR>
     * @@return String
     */
    public String getExtDiv2()
    {
        return this.variousInformParams.getExtDiv2();
    }
}
@
