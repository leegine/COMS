head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformItemMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �e��A�����ڃ}�X�^(WEB3InformItemMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
Revesion History : 2007/6/05 ���g (���u) ���f��No.055�ANo.068
Revesion History : 2007/6/14 ���n�m (���u) ���f��No.086
*/
package webbroker3.inform;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ItemCheckModeDef;
import webbroker3.inform.data.InformCtrlItemMasterParams;
import webbroker3.inform.data.InformCtrlItemMasterRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�e��A�����ڃ}�X�^)<BR>
 * �e��A�����ڃ}�X�^�N���X<BR>
 */
public class WEB3InformItemMaster implements BusinessObject
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformItemMaster.class);

    /**
     * (�e��A�����ڃ}�X�^�s)<BR>
     * �e��A�����ڃ}�X�^�s�I�u�W�F�N�g<BR>
     */
    private InformCtrlItemMasterParams informCtrlItemMasterParams;

    /**
     * @@roseuid 41EE642C02EE
     */
    public WEB3InformItemMaster()
    {

    }

    /**
     * (�e��A�����ڃ}�X�^)<BR>
     * �R���X�g���N�^<BR>
     * �e��A�����ڃ}�X�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�e��A�����ڃ}�X�^�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [�擾����]<BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * ���X�R�[�h�F ����.���X�R�[�h<BR>
     * �A����ʁF ����.�A�����<BR>
     * ���ڕ������F ����.���ڕ�����<BR>
     * 
     * �Q�j�擾�����s�I�u�W�F�N�g��this.�e��A�����ڍs�ɃZ�b�g����B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strInformDiv - (�A�����)<BR>
     * �A�����<BR>
     * 
     * @@param l_strItemSymbolName - (���ڕ�����)<BR>
     * ���ڕ�����<BR>
     * @@roseuid 41BD3EE800B1
     */
    public WEB3InformItemMaster(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strInformDiv,
        String l_strItemSymbolName) throws NotFoundException,WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3InformItemMaster";
        log.entering(STR_METHOD_NAME);
        try
        {
          
            String l_strQuery = "institution_code = ? ";
            l_strQuery += " and branch_code = ?";
            l_strQuery += " and inform_div = ?";
            l_strQuery += " and item_symbol_name = ?";
        
            Object[] l_queryContainer = new Object[] {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strInformDiv,
                l_strItemSymbolName};
                        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                InformCtrlItemMasterRow.TYPE,
                l_strQuery,
                l_queryContainer
                );
            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                throw new NotFoundException(STR_METHOD_NAME);
            }
            InformCtrlItemMasterParams l_params = (InformCtrlItemMasterParams)l_lisRecords.get(0); 
            this.informCtrlItemMasterParams = new InformCtrlItemMasterParams(l_params);
        }

        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }


        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get���ڍŏ���)<BR>
     * ���ڍŏ������擾����B<BR>
     * <BR>
     * this.�e��A�����ڃ}�X�^�s.���ڍŏ�����ԋp����B<BR>
     * @@return Integer
     * @@roseuid 41BD336E0082
     */
    public Integer getItemMin()
    {     
        return this.informCtrlItemMasterParams.item_min;
    }

    /**
     * (get���ڍő咷)<BR>
     * ���ڍő咷���擾����B<BR>
     * <BR>
     * this.�e��A�����ڃ}�X�^�s.���ڍő咷��ԋp����B<BR>
     * @@return Integer
     * @@roseuid 41BD33A902C4
     */
    public Integer getItemMax()
    {
        return this.informCtrlItemMasterParams.item_max;
    }

    /**
     * (get���ڃ`�F�b�N����)<BR>
     * ���ڃ`�F�b�N�������擾����B<BR>
     * <BR>
     * this.�e��A�����ڃ}�X�^�s.���ڃ`�F�b�N������ԋp����B<BR>
     * @@return String
     * @@roseuid 41BD33BF0302
     */
    public String getItemCheckMode()
    {
        return this.informCtrlItemMasterParams.item_check_mode;
    }

    /**
     * (validate�K�{����)<BR>
     * �K�{���ڂɒl�����͂���Ă��邩�̔�����s���B<BR>
     * <BR>
     * �K�{���ڃ`�F�b�N<BR>
     * �@@�|�K�{���ڂŁA�l�����͂���Ă��Ȃ��ꍇ�ithis.is�K�{����() == true && ���ڒl == null�j�A<BR>false��ԋp����B<BR>
     * �@@�|�ȊO�Atrue��ԋp����B<BR>
     * @@param l_item - (���ڒl)<BR>
     * ���ڒl<BR>
     * 
     * @@return boolean
     * @@roseuid 41BD343A0043
     */
    public boolean validateNecessaryItem(Object l_item)
    {
        final String STR_METHOD_NAME = "validateNecessaryItem(Object l_item)";
        log.entering(STR_METHOD_NAME);
        if (this.isNecessaryItem() && l_item == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (validate�L���l)<BR>
     * ���ڂ��L���Ȓl�ł��邩�̔�����s���B<BR>
     * <BR>
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
     * �@@�@@�|WEB3StringTypeUtility.isLetterAndDigit(���ڒl.toString())��ԋp����B<BR>
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
     * �@@�@@�@@�@@�|�ȉ��̕����̂����ꂩ�ɊY�����邩�ƃ`�F�b�N���A���ʂ�ԋp����B<BR>
     *       �S�p�J�i�C���p�ŁC�S�p�p���C���p�p���C�S�p�����C���p�����C<BR>
     *       "-"�C"�["�C"("�C")"�C"�i"�C"�j"�C"�@@"�C" "<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h���[���A�h���X�h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isMailAddress(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�t���O�h�j�̏ꍇ<BR>
     * �@@�@@�|�i���ڒl == Boolean.TRUE || ���ڒl == Boolean.FALSE�j�̏ꍇtrue�A<BR>�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�X�֔ԍ��h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isZipCode(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     * �@@�� �ithis.get���ڃ`�F�b�N����() == �h�d�b�ԍ��^�g�єԍ��h�j�̏ꍇ<BR>
     * �@@�@@�|WEB3StringTypeUtility.isPhoneNumber(���ڒl.toString())��ԋp����B<BR>
     * <BR>
     *   ����L�`�F�b�N�����ȊO�̏ꍇ�́Atrue��ԋp����B<BR> 
     * @@param l_item - (���ڒl)<BR>
     * ���ڒl<BR>
     * @@return boolean
     * @@roseuid 41BD353201F9
     */
    public boolean validateEffectiveValue(Object l_item)
    {
        final String STR_METHOD_NAME = "validateEffectiveValue(Object l_item)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B<BR>
        if (l_item == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�Q�j�@@�L���l�`�F�b�N
        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h�`�F�b�N�Ȃ��h�j�̏ꍇ�Atrue��ԋp����B
        if (WEB3ItemCheckModeDef.DEFAULT.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h���p�����̂݁h�j�̏ꍇ
        //�@@�@@�|WEB3StringTypeUtility.isDigit(���ڒl.toString())��ԋp����B
        else if (WEB3ItemCheckModeDef.HALF_NUMBER.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isDigit(l_item.toString());
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h���p�p�����h�j�̏ꍇ
        //�@@�@@�|WEB3StringTypeUtility.isLetterAndDigit(���ڒl.toString())��ԋp����B
        else if (WEB3ItemCheckModeDef.HALF_ALPHABET_NUMBER.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isLetterAndDigit(l_item.toString());
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h���p�p���h�j�̏ꍇ
        //�@@�@@�|WEB3StringTypeUtility.isLetter(���ڒl.toString())��ԋp����B
        else if (WEB3ItemCheckModeDef.HALF_ALPHABET.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isLetter(l_item.toString());
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h���p�J�i�h�j�̏ꍇ
        //�@@�@@�|WEB3StringTypeUtility.is1byteKanaString(���ڒl.toString())��ԋp����B
        else if (WEB3ItemCheckModeDef.HALF_KANA.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.is1byteKanaString(l_item.toString());
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h�S�p�����h�j�̏ꍇ
        //�@@�@@�|WEB3StringTypeUtility.isWbyteString(���ڒl.toString())��ԋp����B
        else if (WEB3ItemCheckModeDef.FULL_CHARACTER.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isWbyteString(l_item.toString());
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h�Z���^�����J�i�h�j�̏ꍇ
        //�|�ȉ��̕����̂����ꂩ�ɊY�����邩�ƃ`�F�b�N���A���ʂ�ԋp����B<BR>
        //   �S�p�J�i�C���p�ŁC�S�p�p���C���p�p���C�S�p�����C���p�����C<BR>
        //    "-"�C"�["�C"("�C")"�C"�i"�C"�j"�C"�@@"�C" "
        else if (WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return this.isAddressGivenNameKana(l_item.toString());
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h���[���A�h���X�h�j�̏ꍇ
        //�@@�@@�|WEB3StringTypeUtility.isMailAddress(���ڒl.toString())��ԋp����B
        else if (WEB3ItemCheckModeDef.MAIL_ADDRESS.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isMailAddress(l_item.toString());
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h�t���O�h�j�̏ꍇ
        //�@@�@@�|�i���ڒl == Boolean.TRUE || ���ڒl == Boolean.FALSE�j�̏ꍇtrue�A�ȊO��false��ԋp����B
        else if (WEB3ItemCheckModeDef.BOOLEAN_FLAG.equals(this.getItemCheckMode()))
        {
            if (BooleanEnum.TRUE.stringValue().equals(l_item) || BooleanEnum.FALSE.stringValue().equals(l_item))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h�X�֔ԍ��h�j�̏ꍇ
        //�@@�@@�|WEB3StringTypeUtility.isZipCode(���ڒl.toString())��ԋp����B
        else if (WEB3ItemCheckModeDef.ZIP_CODE.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isZipCode(l_item.toString());
        }

        //�@@�� �ithis.get���ڃ`�F�b�N����() == �h�d�b�ԍ��^�g�єԍ��h�j�̏ꍇ
        //�@@�@@�|WEB3StringTypeUtility.isPhoneNumber(���ڒl.toString())��ԋp����B
        else if (WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isPhoneNumber(l_item.toString());
        }

        //  ����L�`�F�b�N�����ȊO�̏ꍇ�́Atrue��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
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
     * �@@�@@�ő咷�Ɏw�肪����ꍇ�ithis.get���ڍő咷() != null�j�A<BR>
     * �@@�@@�ő咷���傫�������O�X�ł��邩�𔻒肷��B<BR>
     * �@@�@@�@@�|�ithis.get���ڍő咷 (���ڒl.toString.length�j�̏ꍇfalse��ԋp����B<BR>
     * @@param l_item - (���ڒl)<BR>
     * ���ڒl<BR>
     * 
     * @@return boolean
     * @@roseuid 41BD368B013D
     */
    public boolean validateLength(Object l_item)
    {
        final String STR_METHOD_NAME = "validateLength(Object l_item)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B
        //
        if (l_item == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //�Q�j�@@���ڌ^�̔���
        //�@@���ڒl�̃f�[�^�^��String�łȂ��ꍇ�Atrue��ԋp����B
        //
        else if (!(l_item instanceof String))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //�R�j�@@���ڃ����O�X�`�F�b�N�@@�����ڒl��String�^�̏ꍇ�̂ݎ��{

        else
        {
            //�@@�R�|�P�j�@@�ŏ����`�F�b�N
            //�@@�@@�ŏ����Ɏw�肪����ꍇ�ithis.get���ڍŏ���() != null�j�A
            //�@@�@@�ŏ������傫�������O�X�ł��邩�𔻒肷��B
            //�@@�@@�@@�|�ithis.get���ڍŏ��� > ���ڒl.toString.length�j�̏ꍇfalse��ԋp����B
            //
            if (this.getItemMin() != null)
            {
                if (this.getItemMin().compareTo(new Integer(l_item.toString().length())) > 0) 
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }
            //�@@�R�|�Q�j�@@�ő咷�`�F�b�N
            //�@@�@@�ő咷�Ɏw�肪����ꍇ�ithis.get���ڍő咷() != null�j�A
            //�@@�@@�ő咷���傫�������O�X�ł��邩�𔻒肷��B
            //�@@�@@�@@�|�ithis.get���ڍő咷 (���ڒl.toString.length�j�̏ꍇfalse��ԋp����B
            if (this.getItemMax() != null)
            {
                if (this.getItemMax().compareTo(new Integer(l_item.toString().length())) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }
            return true;
        }
//        log.entering(STR_METHOD_NAME);
//        return true;
    }

    /**
     * (is�K�{����)<BR>
     * �K�{���ڂ��ǂ����𔻒肷��B<BR>
     * <BR>
     * this.�e��A�����ڃ}�X�^�s.�K�{���ڃt���O == BooleanEnum.TRUE �̏ꍇ�Atrue<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 41BD331E019B
     */
    public boolean isNecessaryItem()
    {
        final String STR_METHOD_NAME = "isNecessaryItem()";
        log.entering(STR_METHOD_NAME);
        if (this.informCtrlItemMasterParams.necessary_flag == BooleanEnum.TRUE.intValue())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        

    }

    /**
     * (is�L���R�[�h�`�F�b�N)<BR>
     * �L���R�[�h�l�`�F�b�N���s�����𔻒肷��B<BR>
     * <BR>
     * �ithis.get���ڃ`�F�b�N����() == �h�L�����������h�j�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 41BD36FD0351
     */
    public boolean isEffectiveCodeCheck()
    {
        final String STR_METHOD_NAME = "isEffectiveCodeCheck()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ItemCheckModeDef.VALID_CODE_CHECK.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�����`�F�b�N)<BR>
     * �����`�F�b�N���s�����𔻒肷��B<BR>
     * <BR>
     * �ithis.get���ڃ`�F�b�N����() == �h���������h�j�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 41BD372E0053
     */
    public boolean isProductCheck()
    {
        final String STR_METHOD_NAME = "isProductCheck()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ItemCheckModeDef.PRODUCT_CHECK.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

    }

    /**
     * (getDefault���ڃ}�X�^)<BR>
     * �w��̒l�Ŋe��A�����ڃ}�X�^�I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �e��A�����ڃ}�X�^Params�𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g���ԋp����B <BR>
     * <BR>
     * �@@�K�{���ڃt���O = ����.�K�{���ڃt���O <BR>
     * �@@���ڍŏ��� = 0�@@ <BR>
     * �@@���ڍő咷 = ����.���ڍő咷 <BR>
     * �@@���ڃ`�F�b�N���� = ����.���ڃ`�F�b�N����<BR>
     * @@param l_blnNecessaryItemFlag - (�K�{���ڃt���O)<BR>
     * �K�{���ڃt���O<BR>
     * 
     * @@param l_intItemMax - (���ڍő咷)<BR>
     * ���ڍő咷<BR>
     * 
     * @@param l_strItemCheckMode - (���ڃ`�F�b�N����)<BR>
     * ���ڃ`�F�b�N����<BR>
     * 
     * @@return WEB3InformItemMaster
     * @@roseuid 41BD3FF000D0
     */
    public static WEB3InformItemMaster getDefaultItemMaster(
        BooleanEnum l_blnNecessaryItemFlag,
        int l_intItemMax,
        String l_strItemCheckMode)
    {
        final String STR_METHOD_NAME = "getDefaultItemMaster";
        log.entering(STR_METHOD_NAME);
        WEB3InformItemMaster l_master = new WEB3InformItemMaster();
        l_master.informCtrlItemMasterParams = new InformCtrlItemMasterParams();
        
        l_master.informCtrlItemMasterParams.setNecessaryFlag(l_blnNecessaryItemFlag.intValue());
        l_master.informCtrlItemMasterParams.setItemMin(0);
        l_master.informCtrlItemMasterParams.setItemMax(l_intItemMax);
        l_master.informCtrlItemMasterParams.setItemCheckMode(l_strItemCheckMode);
        log.exiting(STR_METHOD_NAME);
        return l_master;
    }

    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�e��A�����ڃ}�X�^�s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 41BD32900266
     */
    public Object getDataSourceObject()
    {
        return this.informCtrlItemMasterParams;
    }
    
    private boolean isAddressGivenNameKana(String l_strItemValue)
    {
        if (WEB3StringTypeUtility.isEmpty(l_strItemValue)) 
        {
            return false;
        }
        
        char[] l_chItemValues = l_strItemValue.toCharArray();
        int l_intLength = l_chItemValues.length;
        
        for (int i = 0; i < l_intLength; i++)
        {
            if (!(WEB3StringTypeUtility.isWbyteKanaChar(l_chItemValues[i]) || WEB3StringTypeUtility.is1byteKanaChar(l_chItemValues[i])
                || WEB3StringTypeUtility.isWbyteEng(l_chItemValues[i]) || WEB3StringTypeUtility.isSingleEng(l_chItemValues[i])
                || WEB3StringTypeUtility.isWbyteNum(l_chItemValues[i]) || WEB3StringTypeUtility.isSingleNum(l_chItemValues[i])
                || '-' == l_chItemValues[i] || '�[' == l_chItemValues[i] || '(' == l_chItemValues[i] || ')' == l_chItemValues[i]
                || '�i' == l_chItemValues[i] || '�j' == l_chItemValues[i] || '�@@' == l_chItemValues[i] || ' ' == l_chItemValues[i]))
            {
                return false;
            }
        }
        
        return true;
    }

    /**
     * (is���M�����`�F�b�N)<BR>
     * ���M�����`�F�b�N���s�����𔻒肷��B<BR>
     * <BR>
     * �ithis.get���ڃ`�F�b�N����() == �h���M���������h�j�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isMutualProductCheck()
    {
        final String STR_METHOD_NAME = "isMutualProductCheck()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ItemCheckModeDef.MUTUALFUND_PRODUCT_CHECK.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�������`�F�b�N )<BR>
     * �������`�F�b�N���s�����𔻒肷��B<BR>
     * <BR>
     * �ithis.get���ڃ`�F�b�N����() == �h�����������h�j�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isBondProductCheck()
    {
        final String STR_METHOD_NAME = "isBondProductCheck()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ItemCheckModeDef.BOND_PRODUCT_CHECK.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}
@
