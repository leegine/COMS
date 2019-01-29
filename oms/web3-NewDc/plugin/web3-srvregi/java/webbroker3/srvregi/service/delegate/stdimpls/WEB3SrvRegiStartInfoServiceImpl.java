head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStartInfoServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�N�����T�[�r�XImpl(WEB3SrvRegiStartInfoServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 ���o�� �V�K�쐬
Revesion History : 2005/10/05 ��؁@@���R�I(SRA) �g�����X�����N�Ή�
Revesion History : 2005/10/18 ��؁@@���R�I(SRA) �t�B�f���e�B�Ή�
Revesion History : 2006/06/14 �s�p�@@�d�l�ύX�E���f��No.229�Ή�
Revesion History : 2005/10/18 �s�p�@@(���u) �d�l�ύX���f��No.230,231�Ή�
Revesion History : 2009/04/28 �Ԑi�@@(���u) �d�l�ύX���f��No.410,411�Ή�
Revesion History : 2009/05/20 �đo�g(���u) �d�l�ύX���f��No.419,422�Ή�
Revesion History : 2009/05/31 �đo�g(���u) �d�l�ύX���f��No.425
Revesion History : 2009/06/01 �����@@����(SCS) �d�l�ύX���f��No.426
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HashCalHowToDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.login.service.delegate.WEB3DigestKey;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalHowToDivValueDef;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalOrderDivDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�N�����T�[�r�XImpl) <BR>
 * �T�[�r�X���p�N�����T�[�r�X�����N���X <BR>
 *
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiStartInfoServiceImpl implements WEB3SrvRegiStartInfoService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStartInfoServiceImpl.class);

    /**
     * (�P���~���j�A���Í����v�Z�l) <BR>
     * �i�I���b�N�X�،� �P���~���j�A���p�萔�j <BR>
     * get�Í����ڋq�R�[�h�ɂĎg�p <BR>
     */
    private static final int KENMILLENNIUM_CRYPT_CALC_VALUE = 333221;

    /**
     * (�n�b�V���v�Z�p���t�ؑ֊����) <BR>
     * �n�b�V���v�Z���ɗp������t�́A�S�Ћ��ʂ� <BR>
     * 16:30:00����ɗ����ɐ؂�ւ�����B <BR>
     */
    private static final int DATE_CONVERSION_CONSTANT_TIME_FOR_HASH_CALC = 430;

    /**
     * @@roseuid 41EB94E803A9
     */
    public WEB3SrvRegiStartInfoServiceImpl()
    {

    }

    /**
     * (get�Í����ڋq�R�[�h) <BR>
     * �i�I���b�N�X�،� �P���~���j�A���ɂĎg�p�j <BR>
     * <BR>
     * ����.�ڋq�R�[�h�ɈÍ������{���A���̌��ʂ�ԋp����B <BR>
     * <BR>
     * 1) ����.�ڋq�R�[�h���ꕶ�����i�ڋq�R�[�h[0]�`[5]�j�ɕ����B <BR>
     * <BR>
     * 2) �Í��� <BR>
     * 2-1) �ȉ��̇@@�`�E�̌v�Z���ʂ̍��v���Z�o����B <BR>
     * �@@�ڋq�R�[�h[0] * 100 <BR>
     * �A�ڋq�R�[�h[1] <BR>
     * �B�ڋq�R�[�h[2] * 100000 <BR>
     * �C�ڋq�R�[�h[3] * 10000 <BR>
     * �D�ڋq�R�[�h[4] * 1000 <BR>
     * �E�ڋq�R�[�h[5] * 10 <BR>
     * <BR>
     * 2-2) �ȉ����Z�o����B <BR>
     * �@@2-1)�̌v�Z���ʁ��P���~���j�A���Í����v�Z�l�̏ꍇ <BR>
     * 2-1)�̌v�Z���ʁ|�P���~���j�A���Í����v�Z�l���Z�o <BR>
     * �A��L�ȊO�̏ꍇ <BR>
     * 2-1)�̌v�Z���ʁ|�P���~���j�A���Í����v�Z�l�{1000000���Z�o <BR>
     * <BR>
     * 2-3) �U���ɒ��� <BR>
     * 2-2)�̌v�Z���ʂ��U���ɖ����Ȃ��ꍇ�A�擪��"0"�Ŗ��ߕԋp����B <BR>
     * <BR>
     * EX) 2-2)�̌v�Z���ʂ� 123 �������ꍇ�A000123�Ƃ���B <BR>
     *
     * @@param l_strMainAccountCode -
     *            (�ڋq�R�[�h)
     * @@return String
     * @@roseuid 41B58B0702B1
     */
    public String getCryptAccountCode(String l_strMainAccountCode)
    {
        String STR_METHOD_NAME = " getEncryptedMainAccountCode(String)";
        log.entering(STR_METHOD_NAME);

        //1) ����.�ڋq�R�[�h���ꕶ�����i�ڋq�R�[�h[0]�`[5]�j�ɕ����B
        int l_int0 = Integer.parseInt(l_strMainAccountCode.substring(0, 1));
        int l_int1 = Integer.parseInt(l_strMainAccountCode.substring(1, 2));
        int l_int2 = Integer.parseInt(l_strMainAccountCode.substring(2, 3));
        int l_int3 = Integer.parseInt(l_strMainAccountCode.substring(3, 4));
        int l_int4 = Integer.parseInt(l_strMainAccountCode.substring(4, 5));
        int l_int5 = Integer.parseInt(l_strMainAccountCode.substring(5, 6));

        //2-1) �ȉ��̇@@�`�E�̌v�Z���ʂ̍��v���Z�o����B
        int l_intCnt = l_int0 * 100 + l_int1 + l_int2 * 100000 + l_int3 * 10000 + l_int4 * 1000 + l_int5 * 10;

        //�@@2-1)�̌v�Z���ʁ��P���~���j�A���Í����v�Z�l�̏ꍇ
        int l_intReturn = 0;
        if (l_intCnt >= KENMILLENNIUM_CRYPT_CALC_VALUE)
        {
            log.debug("2-1)�̌v�Z���ʁ��P���~���j�A���Í����v�Z�l�̏ꍇ");
            l_intReturn = l_intCnt - KENMILLENNIUM_CRYPT_CALC_VALUE;
        }
        else
        {
            log.debug("2-1)�̌v�Z����<�P���~���j�A���Í����v�Z�l�̏ꍇ");
            l_intReturn = l_intCnt - KENMILLENNIUM_CRYPT_CALC_VALUE + 1000000;
        }

        //2-3) �U���ɒ���
        String l_strReturn = Integer.toString(l_intReturn);

        if (l_strReturn.length() < 6)
        {
            log.debug("2-3) �U���ɒ���");
            int l_int = 6 - l_strReturn.length();
            for (int i = 0; i < l_int; i++)
            {
                l_strReturn = "0" + l_strReturn;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (create�n�b�V���l) <BR>
     * ��������w�肳�ꂽ���x���_�[�T�[�r�X�̃n�b�V���l�����߂�B <BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jcreate�n�b�V���l�v�Q�� <BR>
     * ========================================================<BR>
     * �@@�V�[�P���X�}�F�i�T�[�r�X���p�jcreate�n�b�V���l<BR>
     * �@@��̈ʒu�@@�@@�F get�t���敪( )�̖߂�l=null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�t���敪��null�ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@class�F WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�F BUSINESS_ERROR_03160<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_strInstitutionCode -
     *            �،���ЃR�[�h
     * @@param l_strSrvDiv -
     *            �T�[�r�X�敪
     * @@param l_strBranchCode -
     *            ���X�R�[�h
     * @@param l_strMainAccountCode -
     *            �ڋq�R�[�h
     * @@param l_tsCurrentTimestamp -
     *            ���ݓ��t
     * @@param l_strMarketCode
     *            �s��R�[�h
     * @@param l_strProductCode
     *            �����R�[�h
     * @@param l_strType
     *            �^�C�v
     * @@param l_digestKey - (�_�C�W�F�X�g�L�[)<BR>
     * �_�C�W�F�X�g�L�[
     * @@param l_strSSIDValue - (SSID�l)<BR>
     * SSID�l
     * @@return String
     * @@roseuid 417E376801B4
     */
    public String createHashValue(
        String l_strInstitutionCode, 
        String l_strSrvDiv,
        String l_strBranchCode, 
        String l_strMainAccountCode, 
        Timestamp l_tsCurrentTimestamp, 
        String l_strMarketCode,
        String l_strProductCode, 
        String l_strType,
        WEB3DigestKey l_digestKey,
        String l_strSSIDValue) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " createHashValue(String, String, String, String, Timestamp, " +
            "String, String, String, WEB3DigestKey, String)";
        log.entering(STR_METHOD_NAME);

        //1.1 get�T�[�r�X�}�X�^�[
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //1.2 get�n�b�V���v�Z�菇�敪
        String l_strHashCalOrderDiv = l_srvRegiServiceMaster.getHashCalOrderDiv();
        log.debug("l_strHashCalOrderDiv = " + l_strHashCalOrderDiv);

        //1.3get�n�b�V���l�ꗗ
        WEB3SrvRegiServiceUseKey[] l_srvRegiServiceUseKey = l_srvRegiServiceMaster.getHashList();

        //1.4 get�n�b�V���v�Z�����敪
        String l_strHashCalHowToDiv = l_srvRegiServiceMaster.getHashCalHowToDiv();
        log.debug("l_strHashCalHowToDiv = " + l_strHashCalHowToDiv);

        //1.5 replace�n�b�V���v�Z����
        String l_strHashCalHowTo = this.replaceHashCalHowTo(l_strHashCalHowToDiv);
        log.debug("l_strHashCalHowTo = " + l_strHashCalHowTo);

        String l_strSrvUseKey0 = null;
        if (l_srvRegiServiceUseKey.length > 0 && l_srvRegiServiceUseKey[0] != null)
        {
            l_strSrvUseKey0 = l_srvRegiServiceUseKey[0].getSrvUseKey();
            log.debug("l_strSrvUseKey0 = " + l_strSrvUseKey0);
        }

        String l_strSrvUseKey1 = null;
        if (l_srvRegiServiceUseKey.length > 1 && l_srvRegiServiceUseKey[1] != null)
        {
            l_strSrvUseKey1 = l_srvRegiServiceUseKey[1].getSrvUseKey();
            log.debug("l_strSrvUseKey1 = " + l_strSrvUseKey1);
        }

        List l_lis = new ArrayList();

		//��Q�Ή� �N�����t�s���Ή�
        //1.6 get������t(Timestamp)
        
		//������t�@@getControl() �ʏ�v�Z(1),�ʏ�v�Z(2)�Ŏg�p
		Date l_datControlDate = this.getControlTimestamp(l_tsCurrentTimestamp);
		//������t�@@format("yyyyMMdd")
		String l_strControlDate = WEB3DateUtility.formatDate(l_datControlDate, "yyyyMMdd");
		log.debug("������t�F"+l_strControlDate);
		
		//�����t�@@format("yyyyMMddHHmm") �Q�i�K�v�Z�Ŏg�p
		String l_strCurrentDate = WEB3DateUtility.formatDate(l_tsCurrentTimestamp, "yyyyMMddHHmm");
		log.debug("�����t�F"+l_strCurrentDate);

        //1.7 ��get�n�b�V���v�Z�菇�敪()���h���Q�i�K�v�Z���h�̏ꍇ��
        if (WEB3SrvRegiHashCalOrderDivDef.TWO_STEP_CALCULATION.equals(l_strHashCalOrderDiv))
        {
            log.debug("��get�n�b�V���v�Z�菇�敪()���h���Q�i�K�v�Z���h�̏ꍇ��");
			log.debug("�g�p���t�F" + l_strCurrentDate);
            //1.7.1 createHashValue
            l_lis.add(l_strSrvUseKey0);
            l_lis.add(l_strMarketCode);
            l_lis.add(l_strProductCode);
            l_lis.add(l_strType);
            String l_strHashAccountId = createAccountCodeHashValue(l_strHashCalHowTo, l_strInstitutionCode,
                l_strBranchCode, l_strMainAccountCode);
            l_lis.add(l_strHashAccountId);
            l_lis.add(l_strCurrentDate);
            l_lis.add(l_strSrvUseKey1);
        }
        //1.8 ��get�n�b�V���v�Z�菇�敪()���h���ʏ�v�Z�i�P�j���h�̏ꍇ��
        else if (WEB3SrvRegiHashCalOrderDivDef.NORMAL1.equals(l_strHashCalOrderDiv))
        {
            log.debug("��get�n�b�V���v�Z�菇�敪()���h���ʏ�v�Z�i�P�j���h�̏ꍇ��");
			log.debug("�g�p���t�F" + l_strControlDate);
            l_lis.add(l_strControlDate);
            l_lis.add(l_strSrvUseKey0);
            l_lis.add(l_strInstitutionCode);
            l_lis.add(l_strBranchCode);
            l_lis.add(l_strMainAccountCode);
            l_lis.add(l_strSrvUseKey1);
        }
        //1.9 ��get�n�b�V���v�Z�菇�敪()���h���ʏ�v�Z�i�Q�j���h�̏ꍇ��
        else if (WEB3SrvRegiHashCalOrderDivDef.NORMAL2.equals(l_strHashCalOrderDiv))
        {
            log.debug("��get�n�b�V���v�Z�菇�敪()���h���ʏ�v�Z�i�Q�j���h�̏ꍇ��");
			log.debug("�g�p���t�F" + l_strControlDate);
            l_lis.add(l_strControlDate);
            l_lis.add(l_strSrvUseKey0);
        }
        //1.10 ��get�n�b�V���v�Z�菇�敪()���h�����O�C���F�؁��h�̏ꍇ��
        else if (WEB3SrvRegiHashCalOrderDivDef.LOGIN_CERTIFICATION.equals(l_strHashCalOrderDiv))
        {
            log.debug("��get�n�b�V���v�Z�菇�敪()���h�����O�C���F�؁��h�̏ꍇ��");
            //����.�_�C�W�F�X�g�L�[���A�L�[4(SHA1�R�[�h)���擾���A�ԋp����B
            return l_digestKey.getKey4();
        }
        //get�n�b�V���v�Z�菇�敪()���h���V���O���T�C���I���A�g���h�̏ꍇ
        else if (WEB3SrvRegiHashCalOrderDivDef.SINGLE_SIGNON_COOPERATION.equals(l_strHashCalOrderDiv))
        {
            log.debug("��get�n�b�V���v�Z�菇�敪()���h�����V���O���T�C���I���A�g���i7�j���h�̏ꍇ��");
            log.debug("�g�p���t�F" + l_strControlDate);

            //get�t���敪( )
            String l_strAdditionDiv = l_srvRegiServiceMaster.getAdditionDiv();

            // get�t���敪( )�̖߂�l=null�̏ꍇ�A�u�t���敪��null�ł��B�v��O���X���[����B
            if (l_strAdditionDiv == null)
            {
                log.debug("�t���敪��null�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03160,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�t���敪��null�ł��B");
            }

            //������̔z����ȉ��̏��ŃZ�b�g����B
            //get�t���敪( )�̖߂�l + ����.�ڋq�R�[�h.subString(0,6)
            String l_strAddDivMainAccount = l_strAdditionDiv;
            if (l_strMainAccountCode != null && l_strMainAccountCode.length() > 5)
            {
                l_strAddDivMainAccount = l_strAddDivMainAccount +
                    l_strMainAccountCode.substring(0, 6);
            }
            l_lis.add(l_strAddDivMainAccount);
            //get�n�b�V���l�ꗗ�̖߂�l�̍ŏ��̂P����.get�T�[�r�X���p�L�[()�̖߂�l
            l_lis.add(l_strSrvUseKey0);

            //����.SSID�l
            l_lis.add(l_strSSIDValue);

            //����.�_�C�W�F�X�g�L�[.getKey1�̖߂�l�B
            l_lis.add(l_digestKey.getKey1());

            //get�n�b�V���l�ꗗ�̖߂�l�̂�2����.get�T�[�r�X���p�L�[()�̖߂�l�B
            l_lis.add(l_strSrvUseKey1);
        }

        String[] l_str = new String[l_lis.size()];
        l_lis.toArray(l_str);

        //1.11 createHashValue
        String l_strReturn = WEB3StringTypeUtility.createHashValue(l_strHashCalHowTo, l_str);
        log.debug("l_strReturn = " + "*" +l_strReturn.hashCode() + "*");
        
        //get�n�b�V���v�Z�菇�敪()���h���V���O���T�C���I���A�g���h�̏ꍇ
        if(WEB3SrvRegiHashCalOrderDivDef.SINGLE_SIGNON_COOPERATION.equals(l_strHashCalOrderDiv))
        {
            //�啶���ɕϊ����ĕԋp
            log.exiting(STR_METHOD_NAME);
            return l_strReturn.toUpperCase();
         }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strReturn;
        }
    }

    /**
     * (replace�n�b�V���v�Z����) <BR>
     * �����Ŏw�肳�ꂽ�n�b�V���v�Z�����敪���A <BR>
     * MessageDigest�I�u�W�F�N�g�������Ɏg�p���镶����ւƕϊ�����B <BR>
     * <BR>
     * ������.�n�b�V���v�Z�����敪���h1:MD2�h�̏ꍇ <BR>
     * "MD2"��ԋp����B <BR>
     * <BR>
     * ������.�n�b�V���v�Z�����敪���h2:MD5�h�̏ꍇ <BR>
     * "MD5"��ԋp����B <BR>
     * <BR>
     * ������.�n�b�V���v�Z�����敪���h3:SHA-1�h�̏ꍇ <BR>
     * "SHA-1"��ԋp����B <BR>
     * <BR>
     * ������.�n�b�V���v�Z�����敪���h4:SHA-256�h�̏ꍇ <BR>
     * "SHA-256"��ԋp����B <BR>
     * <BR>
     * ������.�n�b�V���v�Z�����敪���h5:SHA-384�h�̏ꍇ <BR>
     * "SHA-384"��ԋp����B <BR>
     * <BR>
     * ������.�n�b�V���v�Z�����敪���h6:SHA-512�h�̏ꍇ <BR>
     * "SHA-512"��ԋp����B <BR>
     *
     * @@param l_strHashCalcMethodDiv -
     *            �n�b�V���v�Z�����敪
     * @@return String
     * @@roseuid 41B6CEDA03D7
     */
    public String replaceHashCalHowTo(String l_strHashCalHowToDiv)
    {
        String STR_METHOD_NAME = " replaceHashCalHowTo(String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3HashCalHowToDivDef.MD2.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.MD2;
        }
        else if (WEB3HashCalHowToDivDef.MD5.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.MD5;
        }
        else if (WEB3HashCalHowToDivDef.SHA_1.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.SHA_1;
        }
        else if (WEB3HashCalHowToDivDef.SHA_256.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.SHA_256;
        }
        else if (WEB3HashCalHowToDivDef.SHA_384.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.SHA_384;
        }
        else if (WEB3HashCalHowToDivDef.SHA_512.equals(l_strHashCalHowToDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3SrvRegiHashCalHowToDivValueDef.SHA_512;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get������t) <BR>
     * ����.���ݓ��t�̎��ԕ����𔻒肵�A <BR>
     * �n�b�V���v�Z�E�\���ϊ��̍ۂɁu���ݓ��t�v�Ƃ��ėp������t��ԋp����B <BR>
     * <BR>
     * 1) ����.���ݓ��t�̎��ԕ�����hhmm�̐��l�Ƃ��Ď擾����B <BR>
     * <BR>
     * 2) 1)�Ŏ擾�������l���n�b�V���v�Z�p���t�ؑ֊���Ԃ̏ꍇ <BR>
     * ����.���ݓ��t�|�P���̓��t��ԋp����B <BR>
     * <BR>
     * 3) 1)�Ŏ擾�������l���n�b�V���v�Z�p���t�ؑ֊���Ԃ̏ꍇ <BR>
     * ����.���ݓ��t��ԋp����B <BR>
     *
     * @@param l_tsCurrentTimestamp -
     *            ���ݓ��t
     * @@return java.util.Date
     * @@roseuid 41BE4F7701F5
     */
    public Date getControlTimestamp(Timestamp l_tsCurrentTimestamp)
    {
        String STR_METHOD_NAME = " getCurrentTimestamp(Timestamp)";
        log.entering(STR_METHOD_NAME);
		
		//��Q�Ή� �N�����t�s���Ή�
        //1) ����.���ݓ��t�̎��ԕ�����hhmm�̐��l�Ƃ��Ď擾����B
		Calendar l_cal = Calendar.getInstance();
		l_cal.setTimeInMillis(l_tsCurrentTimestamp.getTime());
		
		int l_intCurrentDate = 
			l_cal.get(Calendar.HOUR_OF_DAY) * 100 + l_cal.get(Calendar.MINUTE);
		
		log.debug("�����t(hh:ss)�F" + l_intCurrentDate);
		log.debug("�n�b�V���v�Z�p���t�ؑ֊���ԁF" + DATE_CONVERSION_CONSTANT_TIME_FOR_HASH_CALC);

        //1)�Ŏ擾�������l���n�b�V���v�Z�p���t�ؑ֊���Ԃ̏ꍇ
        if (l_intCurrentDate < DATE_CONVERSION_CONSTANT_TIME_FOR_HASH_CALC)
        {
        	log.debug("������t�g�p");
            log.exiting(STR_METHOD_NAME);
            return WEB3DateUtility.addDay(l_tsCurrentTimestamp, -1);
        }
        else
        {
			log.debug("�����t�g�p");
            log.exiting(STR_METHOD_NAME);
            return l_tsCurrentTimestamp;
        }
    }
    
    /**
     * �icreate�ڋqID�n�b�V���l�j<BR>
     * ����.�ڋq�R�[�h�A�،���ЃR�[�h�A���X�R�[�h����<BR>
     * �ڋq���擾���A�ڋq.�ڋqID������.�n�b�V���v�Z�����敪��<BR>
     * �n�b�V�������ĕԋp����B<BR>
     * 
     * @@param l_strHashCalHowTo
     *            �n�b�V���v�Z�����敪
     * @@param l_strInstitusionCode
     *            �،���ЃR�[�h
     * @@param l_strBranchCode
     *            ���X�R�[�h
     * @@param l_strAccountCode
     *            �ڋq�R�[�h
     * @@return String
     *
     */
    public String createAccountCodeHashValue(String l_strHashCalHowTo, String l_strInstitutionCode,
                String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createAccountCodeHashValue(String, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();     
        MainAccount l_mainAccount;
        try
        {
            long l_longInstitutionId = l_accMgr.getInstitution(l_strInstitutionCode).getInstitutionId();
            l_mainAccount = l_accMgr.getMainAccount(l_longInstitutionId, l_strBranchCode, l_strAccountCode);
        }
        catch (NotFoundException l_exc)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }
        String l_strAccountId = Long.toString(l_mainAccount.getAccountId());
        String[] l_str = {l_strAccountId};
        
        String l_strHashAccountId = WEB3StringTypeUtility.createHashValue(l_strHashCalHowTo, l_str);
        
        return l_strHashAccountId;
    
    }
    
    /**
     *  (get�Í����ۗL�������j
     * �@@(�t�B�f���e�B�،��ɂĎg�p)
     * �⏕��������Y���ڋq�ۗ̕L�������擾���A
     * �擾�����ۗL���������Í������ĕԋp����B
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jget�Í����ۗL�������v�Q�� <BR>
     * 
     * @@param l_strInstitusionCode
     *            �،���ЃR�[�h
     * @@param l_strBranchCode
     *            ���X�R�[�h
     * @@param l_strAccountCode
     *            �ڋq�R�[�h
     * @@return String
     *
     */
    public String getEncryptionMfAsset(String l_strInstitutionCode,
                String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEncryptionMfAsset";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //�g���A�J�E���g�}�l�[�W�����擾����B
        WEB3GentradeAccountManager l_gentradeAccountManaer = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        WEB3GentradeMainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        try
        {
            //�ڋq���擾����B
            long l_longInstitutionId = l_gentradeAccountManaer.getInstitution(l_strInstitutionCode).getInstitutionId();
            l_mainAccount = (WEB3GentradeMainAccount)l_gentradeAccountManaer.
                                getMainAccount(l_longInstitutionId, l_strBranchCode, l_strAccountCode);
                                
            //�擾�����ڋq�I�u�W�F�N�g.is�M�p�����J��()���R�[������B
            // ���jis�M�p�����J�݂ɓn������ 
            //     �ٍϋ敪���h0�F�w��Ȃ� 
            boolean l_blnisMarginAccountEstablished =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                
            //is�M�p�����J��=true �̏ꍇ�A�ȉ����w�肷��B 
            // SubAccountTypeEnum.�����M�p��������i�ۏ؋��j 
            if (l_blnisMarginAccountEstablished == true)
            {
                log.exiting(STR_METHOD_NAME);
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            else
            {
                //is�M�p�����J��=false �̏ꍇ�A�ȉ����w�肷��B 
                //SubAccountTypeEnum.������������i�a����j
                log.exiting(STR_METHOD_NAME);
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_exc)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }
        
        //�g�����M�|�W�V�����}�l�[�W�����擾����B
        WEB3MutualFundPositionManager l_mutualFundPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
        //�ۗL���Y�����ꗗ���擾����B 
        //  [����] 
        //    �⏕�����F�@@get�⏕����()�̖߂�l 
        //    �\�[�g�L�[�F�@@����ID�@@���� 
        //    �����^�C�v�F�@@ProductTypeEnum.�����M��
        DefaultSortKeySpec l_sortKeySpec = new DefaultSortKeySpec("product_id ASC");;
        List l_lisAssets = l_mutualFundPositionMgr.getAssets(
                l_subAccount,
                l_sortKeySpec,
                ProductTypeEnum.MUTUAL_FUND); 
       
        String l_strMutualFundAssets = null;
        int l_intNumber = 1;
               
        //getAssets()�̖߂�l�̗v�f(=�ۗL���YParams)����Loop���� 
        int l_intAssetsLength = 0;
        if(l_lisAssets != null)
        {
            l_intAssetsLength = l_lisAssets.size();
        }
        
        for(int i = 0; i < l_intAssetsLength; i++)
        {
            MutualFundAsset l_mfAsset = (MutualFundAsset) l_lisAssets.get(i);
            AssetRow l_assetRow = (AssetRow) l_mfAsset.getDataSourceObject();
           
            //����ID���擾����B
            long l_lngProductId = l_assetRow.getProductId();

            //�g�����M�����}�l�[�W�����擾����B
            WEB3MutualFundProductManager l_mutualFundProductManager = null;
            l_mutualFundProductManager =
               (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
                
            //�|�g�����M�����}�l�[�W��.get���M����()���R�[�����A�g�����M�������擾����B
            WEB3MutualFundProduct l_mutualFundProduct = null; //�g�����M����
            try
            {
                //�����I�u�W�F�N�g���擾����B  
                // [����] 
                // ����ID�F�@@�����Ώۂۗ̕L���YParams.getProductId()
                Product l_product = 
                l_mutualFundProductManager.getProduct(l_lngProductId);
               
                //�g�����M�������擾����B
                // [����] 
                // ����Row�F�@@getProduct()�̖߂�l
                l_mutualFundProduct = 
                    (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
                        (MutualFundProductRow) l_product.getDataSourceObject());
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__ �����I�u�W�F�N�g���擾�ł��Ȃ�!");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
               
            //�����R�[�h���擾����B
            String l_strMutualFundProductCode = l_mutualFundProduct.getProductCode();
            log.debug("�����R�[�h = " + l_strMutualFundProductCode);
            
            //���YID���擾����B
            long l_longAssetId = l_assetRow.getAssetId();
            String l_strAssetId = Long.toString(l_longAssetId);
                    
            //���\�c���������擾����B 
            BigDecimal l_bdSellPossibleQty =
                new BigDecimal(Double.toString(l_mutualFundPositionMgr.calcSellPossiblePositionQty(
                    l_subAccount, l_mutualFundProduct, l_strAssetId)));
            l_bdSellPossibleQty = l_bdSellPossibleQty.setScale(2,BigDecimal.ROUND_HALF_UP);
            String l_strSellPossibleQty = WEB3StringTypeUtility.formatNumber(l_bdSellPossibleQty.doubleValue());
            log.debug("���\�c������ = " + l_strSellPossibleQty);
            
            //������z���擾����B
            BigDecimal l_bdSellValue = new BigDecimal(Double.toString(l_mutualFundProduct.getSellValue()));
            l_bdSellValue = l_bdSellValue.setScale(2, BigDecimal.ROUND_HALF_UP);
            String l_strSellValue = WEB3StringTypeUtility.formatNumber(l_bdSellValue.doubleValue());
            log.debug("������z = " + l_strSellValue);
                    
            //�]���z���擾����B
            double l_dblMarketValue = l_mutualFundPositionMgr.calcMarketValue(
                l_subAccount, l_mutualFundProduct, l_strAssetId);
            String l_strMarketValue = WEB3StringTypeUtility.formatNumber(l_dblMarketValue);
            log.debug("�]���z = " + l_strMarketValue);

            //�ۗL������񕶎�����쐬����B
            String l_strMfAsset = null;
            if (l_strMutualFundProductCode != null || l_strSellPossibleQty != null
                    || l_strSellValue != null || l_strMarketValue != null)
            {
                l_strMfAsset = "c" + l_intNumber + "=" + l_strMutualFundProductCode + "&" +
                               "k" + l_intNumber + "=" + l_strSellPossibleQty + "&" +
                               "p" + l_intNumber + "=" + l_strSellValue + "&" +
                               "v" + l_intNumber + "=" + l_strMarketValue;                               
                log.debug("l_strMfAsset = " + l_strMfAsset);
                
                if (l_intNumber == 1)
                {
                    l_strMutualFundAssets = l_strMfAsset;
                }
                else
                {                        
                    l_strMutualFundAssets = l_strMutualFundAssets + "&" + l_strMfAsset;
                }
                
                log.debug("l_strMutualFundAssets = " + l_strMutualFundAssets);
                l_intNumber = l_intNumber + 1;
            }
        }
        
        String l_strEncryptString = null;
        if (l_strMutualFundAssets != null)
        {
            //�`�F�b�N�f�W�b�g���擾����B
            String l_strCeckDigit = createCheckDigit(l_strMutualFundAssets);
            log.debug("�`�F�b�N�f�W�b�g = " + l_strCeckDigit);
        
            //�ۗL���������Í�������B
            String l_strEncryptMutualFundAssets = createEncryption(l_strMutualFundAssets);
            log.debug("�Í������ꂽ�ۗL������� = " + l_strEncryptMutualFundAssets);
        
            //�`�F�b�N�f�W�b�g�{�Í������ꂽ�ۗL�������
            l_strEncryptString = l_strCeckDigit + l_strEncryptMutualFundAssets;
        }
        log.debug("�`�F�b�N�f�W�b�g+�Í������ꂽ�ۗL������� = " + l_strEncryptString);
        
        log.exiting(STR_METHOD_NAME);
        return l_strEncryptString;

    }

    /**
     * (�t�B�f���e�B�،��Í����p�v�Z�l) <BR>
     * �`�F�b�N�f�W�b�g�̏����l 
     */
    private static final int    CHECK_DIGIT_INIT = 601;
    
    /**
     * (�t�B�f���e�B�،��Í����p�v�Z�l) <BR>
     * �`�F�b�N�f�W�b�g�̊��鐔 
     */
    private static final int    CHECK_DIGIT_DIVISION = 1000;

    /**
     * (�t�B�f���e�B�،��Í����p�ϊ��e�[�u��) <BR>
     * �ϊ��e�[�u��
     */
    private static final char[] CONVERT_TBL       
        = {'5','v','G','@@','h','U','*','d','0','=','4','R','p','j','n','s','B','1','&','.','6',
            'A','M','o','i','8','9','w','F','x','Y','l','3','-','k','T','7','q','c','2','e','Z'};

    /**
    *�icreate�Í���������j
    * (�t�B�f���e�B�،��ɂĎg�p)
    *   ����.�ۗL������񕶎�����Í������ĕԋp����
    * 
    *@@param     l_strMfAssetString 
    *                 �ۗL������񕶎���
    *@@return    String �Í������ꂽ�ۗL�������
    */
    private String createEncryption(String l_strMfAssetString) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createEncryption";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbEncryption = new StringBuffer();
        // �Y�������̕ϊ��e�[�u���ɂ�����ʒu
        int l_iPosConvertTbl        = -1;
        // �ϊ���̕���
        int l_iSeachPosConvertTbl   = -1;
        // �ϊ��e�[�u���̃T�C�Y
        int p_iLenConvertTbl        = CONVERT_TBL.length;

        for (int i = 0; i < l_strMfAssetString.length(); i++)
        {
            // 1.�ϊ��e�[�u������Y�������̈ʒu���擾
            // 2.�擾�����ʒu�Ɍ��݂̌����v���X
            // 3.�ϊ��e�[�u���̃T�C�Y���z�����ꍇ�擪�ɖ߂鏈��
            // 4.�ϊ��e�[�u������2�Ŏ擾�����ʒu�̕������擾��StringBuffer�ɒǉ�
            l_iPosConvertTbl       = getPosConvertTbl(l_strMfAssetString.charAt(i)); // 1
            l_iSeachPosConvertTbl  = l_iPosConvertTbl + (i+1);                       // 2
            l_iSeachPosConvertTbl = (l_iSeachPosConvertTbl - 1) % p_iLenConvertTbl;  // 3
            l_sbEncryption.append(CONVERT_TBL[l_iSeachPosConvertTbl]);               // 4
        }
            
        log.exiting(STR_METHOD_NAME);
        return l_sbEncryption.toString();

    }

    /**
    * (get�ϊ��e�[�u���j
    * (�t�B�f���e�B�،��ɂĎg�p)
    *   �ϊ��e�[�u�����̊Y�������̈ʒu��ԋp����
    * 
    *@@param     l_charSearchCharacter
    *                 ��������
    *@@return    int  �ϊ��e�[�u�����̊Y�������̈ʒu���i�[����ϐ�
    */
    private int getPosConvertTbl(char l_charSearchCharacter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPosConvertTbl";
        log.entering(STR_METHOD_NAME);
        try
        {
            // �ϊ��e�[�u�����̊Y�������̈ʒu���i�[����ϐ�
            int l_iPosConvertTbl = -1;
            // �ϊ��e�[�u������Y�������̈ʒu���擾����
            for (int i = 0; i < CONVERT_TBL.length; i++)
            {
                if (Character.toUpperCase(l_charSearchCharacter) == Character.toUpperCase(CONVERT_TBL[i]))
                {
                    l_iPosConvertTbl = i;
                    break;
                }
            }
            // �ϊ��e�[�u���ɊY�������������ꍇ�G���[�Ƃ���
            if (l_iPosConvertTbl == -1)
            {
                throw new NotFoundException("�ϊ��e�[�u���ɊY�������񂪂���܂���B");
            }

            // ���ۂ̕ϊ��e�[�u����1����Ȃ̂ŁA1�v���X�������̂�Ԃ�
            log.exiting(STR_METHOD_NAME);
            return l_iPosConvertTbl + 1;
        }
        catch(NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
        
    /**
    * (create�`�F�b�N�f�W�b�g�j
    * (�t�B�f���e�B�،��ɂĎg�p)
    *   ����.�ۗL������񕶎��񂩂�`�F�b�N�f�W�b�g�𐶐�����
    * 
    *@@param     l_strMfAssetString
    *               �ۗL������񕶎���
    *@@return    String  �`�F�b�N�f�W�b�g
    */
    private String createCheckDigit(String l_strMfAssetString) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCheckDigit";
        log.entering(STR_METHOD_NAME);
        // �`�F�b�N�f�W�b�g�i������j
        String l_sCheckDigit = null;
            
        // �`�F�b�N�f�W�b�g�ɏ����l����
        int l_iCheckDigit = CHECK_DIGIT_INIT;
        // �Y�������̕ϊ��e�[�u���ɂ�����ʒu
        int l_iPosConvertTbl        = 0;
        for (int i = 0; i < l_strMfAssetString.length(); i++)
        {
            // �ϊ��e�[�u������Y�������̈ʒu���擾�����Z���Ă���
            l_iPosConvertTbl = getPosConvertTbl(l_strMfAssetString.charAt(i));
            l_iCheckDigit    = l_iCheckDigit + l_iPosConvertTbl;
        }

        // �`�F�b�N�f�W�b�g�擾
        l_iCheckDigit = l_iCheckDigit % CHECK_DIGIT_DIVISION;

        l_sCheckDigit = Integer.toString(l_iCheckDigit);

        // �`�F�b�N�f�W�b�g��3���ɂȂ�悤�[���l�߂���
        if (l_sCheckDigit != null && l_sCheckDigit.length() == 1)
        {
            l_sCheckDigit = "00" + l_sCheckDigit;
        }
        else if (l_sCheckDigit != null && l_sCheckDigit.length() == 2)
        {
            l_sCheckDigit = "0" + l_sCheckDigit;
        }

        log.exiting(STR_METHOD_NAME);
        return l_sCheckDigit;
                
    }

    /**
     * (getCD�L�[)<BR>
     * CD�L�[�i���[�U�[���ʎq�j���쐬���ĕԋp����B<BR>
     * <BR>
     * �P�j �P�i�Œ�l�j+����.���X�R�[�h+����.�ڋq�R�[�h�i6���j��2�{�����l�̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[()���R�[������B<BR>
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�،���ЃR�[�h�F�����̏،���ЃR�[�h<BR>
     * �@@�@@�T�[�r�X�敪�F�����̃T�[�r�X�敪<BR>
     * �@@�@@is�s���b�N�Ffalse<BR>
     * <BR>
     * �R�j�T�[�r�X���p�L�[�I�u�W�F�N�g���擾����B<BR>
     * �@@�R�|�P�j�Q�j�Ŏ擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�n�b�V���l�ꗗ()���R�[������B<BR>
     * �@@�@@�f�[�^���擾�o���Ȃ��ꍇ�A�u�Y���f�[�^�Ȃ��B�v�̗�O���X���[����B<BR>
     * �@@�R�|�Q�j�R�|�P�j�Ŏ擾�����ꗗ����ŏ���1�����擾����B<BR>
     * <BR>
     * �S�j������z��Ɉȉ��̕������ǉ�����B<BR>
     * �@@�S�|�P�j�P�j�Ŏ擾����������<BR>
     * �@@�S�|�Q�j�R�j�Ŏ擾�����T�[�r�X���p�L�[�I�u�W�F�N�g.get�T�[�r�X���p�L�[()�̐擪110�o�C�g<BR>
     * �@@�S�|�R�jthis.get������t()�̖߂�l�i'YYYYMMDD'�ɕϊ��j<BR>
     * �@@�@@[get������t()�����̐ݒ�]<BR>
     * �@@�@@�@@���ݓ��t�Fnew Timestamp(new Date().getTime())<BR>
     * <BR>
     * �T�j�v�Z�������擾����B<BR>
     * �@@�T�|�P�j�Q�j�Ŏ擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�n�b�V���v�Z�����敪�i�j���R�[������B<BR>
     * �@@�T�|�Q�jthis.replace�n�b�V���v�Z����()���R�[������B<BR>
     * <BR>
     * �U�j�n�b�V���l�̐���<BR>
     * �@@�@@WEB3StringTypeUtility.createHashValue()��p���ăn�b�V���l�𐶐�����B<BR>
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�v�Z�����F �T�j�Ŏ擾�����v�Z����<BR>
     * �@@�@@�v�Z�ΏہF�S�j�ɂč쐬����������z��<BR>
     * <BR>
     * �V�j���L�̕�����𐶐�����B<BR>
     * �@@�@@�P�j�̕�����{�U�j�̑O��20�o�C�g<BR>
     * <BR>
     * �W�j�V�j��ԋp����B<BR>
     * <BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCDKey(
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strSrvDiv,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCDKey(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�i�Œ�l�j+����.���X�R�[�h+����.�ڋq�R�[�h�i6���j��2�{�����l�̕�����𐶐�����
        String l_strAccountCodeSub = "";
        if (l_strAccountCode != null)
        {
            l_strAccountCodeSub = l_strAccountCode;
        }
        if (l_strAccountCode != null && l_strAccountCode.length() > 6)
        {
            l_strAccountCodeSub = l_strAccountCode.substring(0, 6);
        }

        long l_lngMergeCode =
            Long.parseLong(1 + l_strBranchCode + l_strAccountCodeSub);
        String l_strMergeCode = l_lngMergeCode * 2 + "";

        //�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^�[()���R�[������B
        //[�����̐ݒ�]
        //�@@�،���ЃR�[�h�F�����̏،���ЃR�[�h
        //�@@�T�[�r�X�敪�F�����̃T�[�r�X�敪
        //�@@is�s���b�N�Ffalse
        WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement =
            new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_serviceInfoManagement.getSrvMaster(
                l_strInstitutionCode, l_strSrvDiv, false);

        //�T�[�r�X���p�L�[�I�u�W�F�N�g���擾����
        WEB3SrvRegiServiceUseKey[] l_serviceUseKeys =
            l_srvRegiServiceMaster.getHashList();
        //�f�[�^���擾�o���Ȃ��ꍇ�A�u�Y���f�[�^�Ȃ��B�v�̗�O���X���[����
        if (l_serviceUseKeys.length == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        //������z��Ɉȉ��̕������ǉ�����B
        List l_lisMergeStrs = new ArrayList();
        //�P�j�Ŏ擾����������
        l_lisMergeStrs.add(l_strMergeCode);

        //�擾�����T�[�r�X���p�L�[�I�u�W�F�N�g.get�T�[�r�X���p�L�[()�̐擪110�o�C�g
        String l_strSrvUseKey = l_serviceUseKeys[0].getSrvUseKey();
        if (WEB3StringTypeUtility.getByteLength(l_strSrvUseKey) > 110)
        {
            l_strSrvUseKey = new String(l_strSrvUseKey.getBytes(), 0, 110);
        }
        l_lisMergeStrs.add(l_strSrvUseKey);

        //this.get������t()�̖߂�l�i'YYYYMMDD'�ɕϊ��j
        //�����̐ݒ�
        //���ݓ��t�Fnew Timestamp(new Date().getTime())
        Timestamp l_tsSystemTime = new Timestamp(new Date().getTime());
        Date l_datControlDate =
            this.getControlTimestamp(l_tsSystemTime);
        String l_strControlDate =
            WEB3DateUtility.formatDate(
                l_datControlDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        l_lisMergeStrs.add(l_strControlDate);

        //�v�Z�������擾����
        //�擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�n�b�V���v�Z�����敪�i�j���R�[������
        String l_strHowToDiv = l_srvRegiServiceMaster.getHashCalHowToDiv();

        //this.replace�n�b�V���v�Z����()���R�[������
        String l_strCalHowTo = this.replaceHashCalHowTo(l_strHowToDiv);

        //�n�b�V���l�̐���
        //WEB3StringTypeUtility.createHashValue()��p���ăn�b�V���l�𐶐�����B
        //[�����̐ݒ�]
        //�v�Z�����F �T�j�Ŏ擾�����v�Z����
        //�v�Z�ΏہF�S�j�ɂč쐬����������z��
        String[] l_strCombines = new String[l_lisMergeStrs.size()];
        l_lisMergeStrs.toArray(l_strCombines);
        String l_strHashValue = WEB3StringTypeUtility.createHashValue(
            l_strCalHowTo,
            l_strCombines);

        //���L�̕�����𐶐�����
        //�P�j�̕�����{�U�j�̑O��20�o�C�g
        if (WEB3StringTypeUtility.getByteLength(l_strHashValue) > 20)
        {
            l_strHashValue = new String(l_strHashValue.getBytes(), 0, 20);
        }
        String l_strReturnValue = l_strMergeCode + l_strHashValue;

        log.exiting(STR_METHOD_NAME);
        return l_strReturnValue;
    }
}@
