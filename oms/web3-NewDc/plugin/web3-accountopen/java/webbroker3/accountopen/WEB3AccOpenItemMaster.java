head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenItemMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݍ��ڃ}�X�^(WEB3AccOpenItemMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/20 ���� (���u) �V�K�쐬
                   2007/03/12 ���� (SCS) �i���f���j122�C��
*/

package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.data.AccOpenItemMasterDao;
import webbroker3.accountopen.data.AccOpenItemMasterParams;
import webbroker3.accountopen.data.AccOpenItemMasterRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ItemCheckModeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�ݍ��ڃ}�X�^)<BR>
 * �����J�ݍ��ڃ}�X�^<BR>
 *                                                          
 * @@author ����
 * @@version 1.0
 */
public class WEB3AccOpenItemMaster implements BusinessObject
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenItemMaster.class);

    /**
     * (�����J�ݍ��ڃ}�X�^�s)<BR>
     * �����J�ݍ��ڃ}�X�^�s<BR>
     * <BR>
     * �� �����J�ݍ��ڃ}�X�^Params�N���X��DDL��莩����������B<BR>
     */
    private AccOpenItemMasterParams accOpenItemMasterParams;

    /**
     * (�����J�ݍ��ڃ}�X�^)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �� �����J�ݍ��ڃ}�X�^Params�N���X��DDL��莩����������B<BR>
     * @@param l_accOpenItemMasterParams - �����J�ݍ��ڃ}�X�^�s�I�u�W�F�N�g
     *
     * @@return webbroker3.accountopen.WEB3AccOpenItemMaster
     * @@roseuid 418730B5012C
     */
    public WEB3AccOpenItemMaster(AccOpenItemMasterParams l_accOpenItemMasterParams)
    {
        this.accOpenItemMasterParams = l_accOpenItemMasterParams;
    }

    /**
     * (�����J�ݍ��ڃ}�X�^)<BR>
     * �R���X�g���N�^�B<BR>
     * �����J�ݍ��ڃ}�X�^�𐶐�����B<BR>
     * <BR>
     * �ȉ��̏����Ō����J�ݍ��ڃ}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����J�ݍ��ڃ}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�����J�ݍ��ڃ}�X�^.���X�R�[�h = ���X�R�[�h And<BR>
     * �@@�����J�ݍ��ڃ}�X�^.�����敪 = �����敪 And<BR>
     * �@@�����J�ݍ��ڃ}�X�^.�`�F�b�N�^�C�v = �`�F�b�N�^�C�v And<BR>
     * �@@�����J�ݍ��ڃ}�X�^.���ڕ����� = ���ڕ�����<BR>
     * <BR>
     * �������ʂ̌����J�ݍ��ڃ}�X�^�s�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountDiv - �����敪<BR>
     * <BR>
     * 0�F�l�A�J�E���g�@@1�F�@@�l�A�J�E���g<BR>
     *
     * @@param l_strValidateType - �`�F�b�N�^�C�v<BR>
     * <BR>
     * 0�FDEFAULT�i�ڋq�\���j�@@<BR>
     * 1�F�Ǘ��Ґ\���@@<BR>
     * 2�F�\���X�V�@@<BR>
     * 3�F�`�[�쐬<BR>
     *
     * @@param l_strItemSymbolName - ���ڕ�����<BR>
     * <BR>
     * ���u�����J�݌����q�e�[�u���v�̗񕨗����B<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenItemMaster
     * @@roseuid 41872F7702A3
     */
    public WEB3AccOpenItemMaster(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountDiv, String l_strValidateType, String l_strItemSymbolName) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenItemMaster(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AccOpenItemMasterRow l_row = AccOpenItemMasterDao.findRowByPk(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, l_strItemSymbolName); //DataNetworkException, DataQueryException
            
            this.accOpenItemMasterParams = new AccOpenItemMasterParams(l_row);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("�������ʂɈ�v����s�����݂��Ȃ�");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�A�N�Z�X�����s�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�A�N�Z�X�����s�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.�����J�ݍ��ڃ}�X�^�s��ԋp����B <BR>
     * @@return Object
     * @@roseuid 41873856012C
     */
    public Object getDataSourceObject()
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.accOpenItemMasterParams;   
    }

    /**
     * (is�K�{����)<BR>
     * �K�{���ڂ��ǂ����𔻒肷��B<BR>
     * <BR>
     * �ithis.�����J�ݍ��ڃ}�X�^�s.�K�{���ڃt���O == BooleanEnum.TRUE�j�̏ꍇ�Atrue<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4187355D0032
     */
    public boolean isNecessaryItem()
    {
        final String STR_METHOD_NAME = " isNecessaryItem()";
        log.entering(STR_METHOD_NAME);
        
        if (this.accOpenItemMasterParams.necessary_flag.equals(BooleanEnum.TRUE))
        {
            log.debug("�ithis.�����J�ݍ��ڃ}�X�^�s.�K�{���ڃt���O == BooleanEnum.TRUE�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.debug("�ithis.�����J�ݍ��ڃ}�X�^�s.�K�{���ڃt���O != BooleanEnum.TRUE�j�̏ꍇ");
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get���ڍŏ���)<BR>
     * ���ڍŏ������擾����B<BR>
     * <BR>
     * this.�����J�ݍ��ڃ}�X�^�s.���ڍŏ�����ԋp����B<BR>
     * @@return Integer
     * @@roseuid 418738B602B2
     */
    public Integer getItemMin()
    {
        final String STR_METHOD_NAME = " getItemMin()";
        log.entering(STR_METHOD_NAME);
        
        if (this.accOpenItemMasterParams.getItemMinIsNull())
        {
            log.debug("NULL��ԋp");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        int l_int = this.accOpenItemMasterParams.getItemMin();
        log.exiting(STR_METHOD_NAME);
        return new Integer(l_int);
    }

    /**
     * (get���ڍő咷)<BR>
     * ���ڍő咷���擾����B<BR>
     * <BR>
     * this.�����J�ݍ��ڃ}�X�^�s.���ڍő咷��ԋp����B<BR>
     * @@return Integer
     * @@roseuid 4187394A0235
     */
    public Integer getItemMax()
    {
        final String STR_METHOD_NAME = " getItemMax()";
        log.entering(STR_METHOD_NAME);
        
        if (this.accOpenItemMasterParams.getItemMaxIsNull())
        {
            log.debug("NULL��ԋp");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        int l_int = this.accOpenItemMasterParams.getItemMax();
        log.exiting(STR_METHOD_NAME);
        return new Integer(l_int);
    }

    /**
     * (get���ڃ`�F�b�N����)<BR>
     * ���ڃ`�F�b�N�������擾����B<BR>
     * <BR>
     * this.�����J�ݍ��ڃ}�X�^�s.���ڃ`�F�b�N������ԋp����B<BR>
     * @@return String
     * @@roseuid 41874B38015B
     */
    public String getItemCheckMode()
    {
        final String STR_METHOD_NAME = " getItemCheckMode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.accOpenItemMasterParams.getItemCheckMode();
    }

    /**
     * (validate�K�{����)<BR>
     * �K�{���ڂɒl�����͂���Ă��邩�̔�����s���B<BR>
     * <BR>
     * �K�{���ڃ`�F�b�N<BR>
     * �@@�|�K�{���ڂŁA�l�����͂���Ă��Ȃ��ꍇ�ithis.is�K�{����() == true && <BR>
     * ���ڒl == null�j�Afalse��ԋp����B<BR>
     * �@@�|�ȊO�Atrue��ԋp����B�@@<BR>
     * @@param l_itemValue - ���ڒl
     * @@return boolean
     * @@roseuid 418733470090
     */
    public boolean validateNecessaryItem(Object l_itemValue)
    {
        final String STR_METHOD_NAME = " validateNecessaryItem(Object)";
        log.entering(STR_METHOD_NAME);
        
        if (this.isNecessaryItem() && l_itemValue == null)
        {
            log.debug("�K�{���ڂŁA�l�����͂���Ă��Ȃ��ꍇ�Afalse��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.debug("true��ԋp����B");
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate�L���l)<BR>
     * ���ڂ��L���Ȓl�ł��邩�̔�����s���B<BR>
     * <BR>
     * �� �L���R�[�h�`�F�b�N�ȊO�B<BR>
     * �� �L���R�[�h�`�F�b�N�́A�����J�ݍ��ڑ���#validate�L���R�[�h�l()�ɂĎ��{�B<BR>
     * <BR>
     * �P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�L���l�`�F�b�N<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�`�F�b�N�Ȃ��h�j�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h���p�����̂݁h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isDigit(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h���p�p�����h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isLetterOrDigit(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h���p�p���h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isLetter(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h���p�J�i�h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.is1byteKanaString(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�S�p�����h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isWbyteString(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�Z���^�����J�i�h�j�̏ꍇ<BR>
     * �@@�@@�|(*2) �ȉ��̕����̂�OK�B <BR>
     *�@@�@@�@@�S�p�J�i�C���p�J�i�C�S�p�p���C���p�p���C�S�p�����C���p�����C <BR>
     *�@@�@@�@@"-"�C"�["�C"("�C")"�C"�i"�C"�j"�C"�@@"�C" "�@@ <BR>
     *�@@�@@���Q�l�j�@@WEB3StringTypeUtility.isWbyteKanaString(���ڒl.toString()) <BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h���[���A�h���X�h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isMailAddress(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�t���O�h�j�̏ꍇ<BR>
     * �@@�@@�|�i���ڒl == Boolean.TRUE || ���ڒl == Boolean.FALSE�j�̏ꍇtrue�A<BR>
     * �ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�X�֔ԍ��h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isZipCode(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�d�b�ԍ��^�g�єԍ��h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isPhoneNumber(���ڒl.toString())��ԋp����B<BR>
     * @@param l_itemValue - ���ڒl
     * @@return boolean
     * @@roseuid 41873ACD0090
     */
    public boolean validateValidValue(Object l_itemValue)
    {
        final String STR_METHOD_NAME = " validateValidValue(Object)";
        log.entering(STR_METHOD_NAME);
        
        if (l_itemValue == null)
        {
            log.debug("�P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B ");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�Q�j�@@�L���l�`�F�b�N 
        log.debug("�Q�j�@@�L���l�`�F�b�N ");
        if (WEB3ItemCheckModeDef.DEFAULT.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h�`�F�b�N�Ȃ��h�j�̏ꍇ�Atrue��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        if (WEB3ItemCheckModeDef.HALF_NUMBER.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h���p�����̂݁h�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isDigit(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.HALF_ALPHABET_NUMBER.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h���p�p�����h�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
//            return WEB3StringTypeUtility.isLetterAndDigit(l_itemValue.toString());
            return WEB3StringTypeUtility.isLetterOrDigit(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.HALF_ALPHABET.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h���p�p���h�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isLetter(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.HALF_KANA.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h���p�J�i�h�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.is1byteKanaString(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.FULL_CHARACTER.equals(this.getItemCheckMode()))
        {
            return true;
//            log.debug("�ithis.get���ڃ`�F�b�N����() == �h�S�p�����h�j�̏ꍇ");
//            log.exiting(STR_METHOD_NAME);
//            return WEB3StringTypeUtility.isWbyteString(l_itemValue.toString());
        }
        
        //Q&A: WEB3-ACCOUNTOPEN-A-UT-0025 & ���f�� V1.4
        //�d�l�ύX�Ǘ��䒠 ���f�� No.017
        if (WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h�Z���^�����J�i�h�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return this.isAddressGivenNameKana(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.MAIL_ADDRESS.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h���[���A�h���X�h�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isMailAddress(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.BOOLEAN_FLAG.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h�t���O�h�j�̏ꍇ");
            
            if (BooleanEnum.TRUE.equals(l_itemValue) || BooleanEnum.FALSE.equals(l_itemValue))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        if (WEB3ItemCheckModeDef.ZIP_CODE.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h�X�֔ԍ��h�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isZipCode(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h�d�b�ԍ��^�g�єԍ��h�j�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isPhoneNumber(l_itemValue.toString());
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate�����O�X)<BR>
     * ���ڂ��L���ȍ��ڒ��͈͓̔��ł��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���ڌ^�̔���<BR>
     * �@@���ڒl�̃f�[�^�^��String�łȂ��ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �R�j�@@���ڃ����O�X�`�F�b�N�@@�����ڒl��String�^�̏ꍇ�̂ݎ��{<BR>
     * �@@�R�|�P�j�@@�ŏ����`�F�b�N<BR>
     * �@@�@@�ŏ����Ɏw�肪����ꍇ�ithis.get���ڍŏ���() != null�j�A<BR>
     * �@@�@@�ŏ������傫�������O�X�ł��邩�𔻒肷��B<BR>
     * �@@�@@�@@�|�ithis.get���ڍŏ��� > ���ڒl.toString.length�j�̏ꍇfalse��ԋp����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�ő咷�`�F�b�N<BR>
     * �@@�@@�ő咷�Ɏw�肪����ꍇ�ithis.get���ڍŏ���() != null�j�A<BR>
     * �@@�@@�ő咷���傫�������O�X�ł��邩�𔻒肷��B<BR>
     * �@@�@@�@@�|�ithis.get���ڍő咷 < ���ڒl.toString.length�j�̏ꍇfalse��ԋp����B<BR>
     * @@param l_itemValue - ���ڒl
     * @@return boolean
     * @@roseuid 41873B0D010D
     */
    public boolean validateLength(Object l_itemValue)
    {
        final String STR_METHOD_NAME = " validateLength(Object)";
        log.entering(STR_METHOD_NAME);
        
        if (l_itemValue == null)
        {
            log.debug("�P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        if (!(l_itemValue instanceof String))
        {
            log.debug("�Q�j���ڒl�̃f�[�^�^��String�łȂ��ꍇ�Atrue��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("�R�j�@@���ڃ����O�X�`�F�b�N�@@�����ڒl��String�^�̏ꍇ�̂ݎ��{");
            if (this.getItemMin() != null && this.getItemMin().intValue() > WEB3StringTypeUtility.getByteLength(l_itemValue.toString()))
            {
                log.debug("�ithis.get���ڍŏ��� > ���ڒl.toString.length�j�̏ꍇfalse��ԋp����B");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            
            if (this.getItemMax() != null && this.getItemMax().intValue() < WEB3StringTypeUtility.getByteLength(l_itemValue.toString()))
            {
                log.debug("�ithis.get���ڍő咷 < ���ڒl.toString.length�j�̏ꍇfalse��ԋp����B");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        
        log.exiting(STR_METHOD_NAME); 
        return true;
    }

    /**
     * (is�L���R�[�h�`�F�b�N)<BR>
     * �L���R�[�h�l�`�F�b�N���s�����𔻒肷��B<BR>
     * <BR>
     * �ithis.get���ڃ`�F�b�N����() == �h�L�����������h�j�̏ꍇtrue�A<BR>
     * �ȊOfalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4193568902B6
     */
    public boolean isValidCodeCheck()
    {
        final String STR_METHOD_NAME = " isValidCodeCheck()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3ItemCheckModeDef.VALID_CODE_CHECK.equals(this.getItemCheckMode()))
        {
            log.debug("�ithis.get���ڃ`�F�b�N����() == �h�L�����������h�j�̏ꍇtrue��ԋp����");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.debug("�ithis.get���ڃ`�F�b�N����() != �h�L�����������h�j�̏ꍇfalse��ԋp����");
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (getDefault���ڃ}�X�^)<BR>
     * �w��̒l�Ō����J�ݍ��ڃ}�X�^�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �����J�ݍ��ڃ}�X�^Params�𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g���ԋp����B<BR>
     * <BR>
     * �@@�K�{���ڃt���O = �K�{���ڃt���O<BR>
     * �@@���ڍŏ��� = 0�@@<BR>
     * �@@���ڍő咷 = ���ڍő咷<BR>
     * �@@���ڃ`�F�b�N���� = ���ڃ`�F�b�N����<BR>
     * @@param l_necessaryItemFlag - TRUE�^�K�{����<BR>
     * FALSE�^�K�{���ڂłȂ�<BR>
     *
     * @@param l_intItemMax - ���ڍő咷
     * @@param l_strItemCheckMode - ���ڃ`�F�b�N����<BR>
     * <BR>
     * 00�FDEFAULT�i�`�F�b�N�Ȃ��j�@@<BR>
     * 01�F�L���R�[�h�l�`�F�b�N(*1)<BR>
     * 02�F���p�����̂݁@@<BR>
     * 03�F���p�p�����@@<BR>
     * 04�F���p�p���@@<BR>
     * 05�F���p�J�i�@@<BR>
     * 06�F�S�p�����@@<BR>
     * 07�F�Z���^�����J�i<BR>
     * 08�F���[���A�h���X�@@<BR>
     * 10�F�X�֔ԍ��@@<BR>
     * 11�F�d�b�^�g�єԍ��@@<BR>
     * 12�F�N��i20�Έȏ�j<BR>
     * 13�F�t���O�iBooleanEnum.TRUE/FALSE�j<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenItemMaster
     * @@roseuid 419359760064
     */
    public static WEB3AccOpenItemMaster getDefaultAccOpenItemMaster(BooleanEnum l_necessaryItemFlag, int l_intItemMax, String l_strItemCheckMode)
    {
        final String STR_METHOD_NAME = " getDefaultAccOpenItemMaster(BooleanEnum, int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�����J�ݍ��ڃ}�X�^Params�𐶐�
        AccOpenItemMasterParams l_accOpenItemMasterParams = new AccOpenItemMasterParams();
        l_accOpenItemMasterParams.setNecessaryFlag(l_necessaryItemFlag);
        l_accOpenItemMasterParams.setItemMin(0);
        l_accOpenItemMasterParams.setItemMax(l_intItemMax);
        l_accOpenItemMasterParams.setItemCheckMode(l_strItemCheckMode);
        
        WEB3AccOpenItemMaster l_accOpenItemMaster = new WEB3AccOpenItemMaster(l_accOpenItemMasterParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_accOpenItemMaster;
    }
    
    private boolean isAddressGivenNameKana(String l_strItemValue)
    {
        if (WEB3StringTypeUtility.isEmpty(l_strItemValue)) 
        {
            return false;
        }
        
        String l_strCheckValue = WEB3StringTypeUtility.convert(l_strItemValue);
        char[] l_chItemValues = l_strCheckValue.toCharArray();
        int l_intLength = l_chItemValues.length;
        
        for (int i = 0; i < l_intLength; i++)
        {
            //Bug U01650
            if (
                !(WEB3StringTypeUtility.isWbyteKanaChar(l_chItemValues[i]) || 
                 WEB3StringTypeUtility.is1byteKanaChar(l_chItemValues[i]) || 
                 WEB3StringTypeUtility.isWbyteEng(l_chItemValues[i]) || 
                 WEB3StringTypeUtility.isSingleEng(l_chItemValues[i])|| 
                 WEB3StringTypeUtility.isWbyteNum(l_chItemValues[i]) || 
                 WEB3StringTypeUtility.isSingleNum(l_chItemValues[i])|| 
                 '-' == l_chItemValues[i] || 
                 '�[' == l_chItemValues[i] || 
                 '(' == l_chItemValues[i] || 
                 ')' == l_chItemValues[i] || 
                 '�i' == l_chItemValues[i] || 
                 '�j' == l_chItemValues[i] || 
                 '�@@' == l_chItemValues[i] || 
                 ' ' == l_chItemValues[i] || 
                 '�|' == l_chItemValues[i] ||
                 0xff0d == l_chItemValues[i]
                 )
                )
            {
                return false;
            }
        }
        
        return true;
    }
}
@
