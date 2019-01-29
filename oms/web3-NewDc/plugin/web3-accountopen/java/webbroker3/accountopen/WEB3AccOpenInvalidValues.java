head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInvalidValues.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݕs��(WEB3AccOpenInvalidValues.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 ���� (���u) �V�K�쐬
*/

package webbroker3.accountopen;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.data.AccOpenInvalidValuesDao;
import webbroker3.accountopen.data.AccOpenInvalidValuesParams;
import webbroker3.accountopen.data.AccOpenInvalidValuesRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݕs��)<BR>
 * �����J�ݕs��<BR>
 *                                                         
 * @@author ����
 * @@version 1.0
 */
public class WEB3AccOpenInvalidValues implements BusinessObject 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenInvalidValues.class);

    /**
     * (�����J�ݕs���s)<BR>
     * �����J�ݕs���s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �����J�ݕs��Params�N���X��DDL��莩����������B<BR>
     */
    private AccOpenInvalidValuesParams accOpenInvalidValuesParams;
    
    /**
     * @@roseuid 41B45E6B008C
     */
    public WEB3AccOpenInvalidValues() 
    {
     
    }
    
    /**
     * (�����J�ݕs��)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �� �����J�ݕs��Params�N���X��DDL��莩����������B<BR>
     * @@param l_accOpenInvalidValuesParams - �����J�ݕs���s�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.WEB3AccOpenInvalidValues
     * @@roseuid 4191B4D00009
     */
    public WEB3AccOpenInvalidValues(AccOpenInvalidValuesParams l_accOpenInvalidValuesParams) 
    {
        this.accOpenInvalidValuesParams = l_accOpenInvalidValuesParams;
    }
    
    /**
     * (�����J�ݕs��)<BR>
     * �R���X�g���N�^<BR>
     * �����J�ݕs���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �ȉ��̏����Ō����J�ݕs���e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����J�ݕs��.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@�����J�ݕs��.���ʃR�[�h = ���ʃR�[�h<BR>
     * <BR>
     * �������ʂ̌����J�ݕs���s�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.accountopen.WEB3AccOpenInvalidValues
     * @@roseuid 4191B47C0330
     */
    public WEB3AccOpenInvalidValues(String l_strInstitutionCode, String l_strRequestNumber) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenInvalidValues(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            AccOpenInvalidValuesRow l_accOpenInvalidValuesRow = AccOpenInvalidValuesDao.findRowByPk(l_strInstitutionCode, l_strRequestNumber);
            
            this.accOpenInvalidValuesParams = new AccOpenInvalidValuesParams(l_accOpenInvalidValuesRow);
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
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createForUpdateParams)<BR>
     * �@@this.�����J�ݕs���s���R�s�[���āA�������e�̕ʃC���X�^���X���쐬����iclone�j�B <BR>
     * �쐬�����R�s�[�����g��this.�����J�ݕs���s�ɃZ�b�g����B <BR>
     * @@roseuid 4191B9C50004
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        AccOpenInvalidValuesParams l_accOpenInvalidValuesParams = new AccOpenInvalidValuesParams(this.accOpenInvalidValuesParams);
        this.accOpenInvalidValuesParams = l_accOpenInvalidValuesParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�s�����ږ�)<BR>
     * �w���index�ɊY������s�����ږ����擾����B<BR>
     * <BR>
     * �� ����index�́A�s�����ڂ̗񖼂ɂȂ��Ă���ԍ��������B<BR>
     * �@@�Ⴆ�΁A�iindex=1�j�̏ꍇ�́Athis.�����J�ݕs���s.�s�����ږ��P ��<BR>
     * �ԋp����B<BR>
     * @@param l_intIndex - index�i�s�����ڂ̔ԍ��j<BR>
     * <BR>
     * �� 1�`10�͈̔͂Ŏw�肷�邱��<BR>
     * 
     * @@return String
     * @@roseuid 4191B794017B
     */
    public String getInvalidItemName(int l_intIndex) 
    {
        final String STR_METHOD_NAME = " getInvalidItemName(int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("field 'item_name" + l_intIndex + "' not found.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("field 'item_name" + l_intIndex + "' not found.");
        }

        log.debug("�s�����ږ�" + l_intIndex + "���擾����B");
        String l_strItem = (String)this.accOpenInvalidValuesParams.getColumn("item_name" + l_intIndex);
        
        log.exiting(STR_METHOD_NAME);
        return l_strItem;
    }
    
    /**
     * (get�R�����g)<BR>
     * �w���index�ɊY������R�����g���擾����B<BR>
     * <BR>
     * �� ����index�́A�s�����ڂ̗񖼂ɂȂ��Ă���ԍ��������B<BR>
     * �@@�Ⴆ�΁A�iindex=1�j�̏ꍇ�́Athis.�����J�ݕs���s.�R�����g�P ��ԋp����B<BR>
     * @@param l_intIndex - index�i�s�����ڂ̔ԍ��j<BR>
     * <BR>
     * �� 1�`10�͈̔͂Ŏw�肷�邱��<BR>
     * 
     * @@return String
     * @@roseuid 4191B8AC00FE
     */
    public String getComment(int l_intIndex) 
    {
        final String STR_METHOD_NAME = " getComment(int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("field 'comment" + l_intIndex + "' not found.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("field 'comment" + l_intIndex + "' not found.");
        }

        log.debug("�R�����g" + l_intIndex + "���擾����B");
        String l_strComment = (String)this.accOpenInvalidValuesParams.getColumn("comment" + l_intIndex);
        
        log.exiting(STR_METHOD_NAME);
        return l_strComment;
    }
    
    /**
     * (is����)<BR>
     * �w���index�ɊY������s�����ڂ��������Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �s�����ڂ��o�^����Ă���ꍇ�A�����t���O�̏�Ԃ�ԋp����B<BR>
     * �A���A�s�����ڂ��o�^����Ă��Ȃ�index�̏ꍇ�́A�������Ă����<BR>
     * ���f����itrue��ԋp����j�B<BR>
     * <BR>
     * �w��index�ɕs�����o�^����Ă��Ȃ��ꍇ�ithis.is�s�����ړo�^(index) == false�j�A<BR>
     * true��ԋp����B<BR>
     * �ȊO�Aindex�ɊY�����銮���t���O�̓��e��Ή�����boolean�l�ɂĕԋp����B<BR>
     * <BR>
     * �� ����index�́A�s�����ڂ̗񖼂ɂȂ��Ă���ԍ��������B<BR>
     * �@@�Ⴆ�΁A�iindex=1�j���w�肳�ꂽ�ꍇ�Athis.�����J�ݕs���s.�����t���O�P��<BR>
     * ����boolean�l��ԋp����B<BR>
     * @@param l_intIndex - index�i�s�����ڂ̔ԍ��j<BR>
     * <BR>
     * �� 1�`10�͈̔͂Ŏw�肷�邱��<BR>
     * 
     * @@return boolean
     * @@roseuid 4191B8CF019A
     */
    public boolean isComplete(int l_intIndex) 
    {
        final String STR_METHOD_NAME = " isComplete(int)";
        log.entering(STR_METHOD_NAME);

        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("field 'comp_flag" + l_intIndex + "' not found.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("field 'comp_flag" + l_intIndex + "' not found.");
        }

        boolean l_blnFlag = false;
        
        log.debug("�s�����ڂ�����" + l_intIndex + "���Ă��邩�𔻒肷��B");
        BooleanEnum l_compFlag = (BooleanEnum)this.accOpenInvalidValuesParams.getColumn("comp_flag" + l_intIndex);
        
        if (BooleanEnum.TRUE.equals(l_compFlag))
        {
            log.debug("�s�����ڂ�����" + l_intIndex + ": " + l_compFlag);
            l_blnFlag = true;
        }
        else if (BooleanEnum.FALSE.equals(l_compFlag))
        {
            log.debug("�s�����ڂ�����" + l_intIndex + ": " + l_compFlag);
            l_blnFlag = false;
        }
        else
        {
            log.debug("�s�����ڂ�����" + l_intIndex + ": " + l_compFlag);
            l_blnFlag = true;
        }

        if (!this.isInvalidItemRegist(l_intIndex))
        {
            log.debug("�w��index�ɕs�����o�^����Ă��Ȃ��ꍇ�ithis.is�s�����ړo�^(index) == false�j�Atrue��ԋp����B");
            l_blnFlag = true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnFlag;
    }
    
    /**
     * (is����)<BR>
     * �s�����ڂ����ׂĊ������Ă��邩�𔻒肷��B<BR>
     * <BR>
     * this.is����(index)��10��R�[������B�iindex�ɂ́A1�`10���w�肷��j<BR>
     * ��ł�false�����ԋp���ꂽ�ꍇ�Afalse��ԋp����B<BR>
     * ���ׂĂ�index��true���ԋp���ꂽ�ꍇ�Atrue��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 419448320206
     */
    public boolean isComplete() 
    {
        final String STR_METHOD_NAME = " isComplete()";
        log.entering(STR_METHOD_NAME);
        
        for (int i = 1; i <= 10; i++)
        {
            if (!this.isComplete(i))
            {
                log.debug("index " + i + " false returned");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        log.debug("���ׂĂ�index��true���ԋp���ꂽ�ꍇ�Atrue��ԋp����B");
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get���l�P)<BR>
     * this.�����J�ݕs���s.���l�P��ԋp����B<BR>
     * @@return String
     * @@roseuid 4191B92B010E
     */
    public String getNote1() 
    {
        final String STR_METHOD_NAME = " getNote1()";
        log.entering(STR_METHOD_NAME);
        
        String l_strNote1 = this.accOpenInvalidValuesParams.getNote1();

        log.exiting(STR_METHOD_NAME);
        return l_strNote1;
    }
    
    /**
     * (get���l�Q)<BR>
     * this.�����J�ݕs���s.���l�Q��ԋp����B<BR>
     * @@return String
     * @@roseuid 4191B93D010E
     */
    public String getNote2() 
    {
        final String STR_METHOD_NAME = " getNote2()";
        log.entering(STR_METHOD_NAME);
        
        String l_strNote2 = this.accOpenInvalidValuesParams.getNote2();

        log.exiting(STR_METHOD_NAME);
        return l_strNote2;
    }
    
    /**
     * (set�s������)<BR>
     * �w���index�ɊY������s�����ځi�s�����ږ��A�R�����g�A�����t���O�j���Z�b�g����B<BR>
     * <BR>
     * �� ����index�́A�s�����ڂ̗񖼂ɂȂ��Ă���ԍ��������B<BR>
     * �@@�Ⴆ�΁A�iindex=1�j�̏ꍇ�́A�ȉ��̒ʂ�Z�b�g���s���B<BR>
     * <BR>
     * �@@this.�����J�ݕs���s.�s�����ږ��P = �s�����ږ�<BR>
     * �@@this.�����J�ݕs���s.�R�����g�P = �R�����g<BR>
     * �@@this.�����J�ݕs���s.�����t���O�P = ��is����()�ɊY������BooleanEnum�l<BR>
     * @@param l_intIndex - index�i�s�����ڂ̔ԍ��j<BR>
     * <BR>
     * �� 1�`10�͈̔͂Ŏw�肷�邱��<BR>
     * 
     * @@param l_strInvalidItemName - �s�����ږ�
     * @@param l_strComment - �R�����g
     * @@param l_blnIsComplete - �s���̏C�����������Ă��邩�̔���
     * @@roseuid 4191B9FD0052
     */
    public void setInvalidItem(int l_intIndex, String l_strInvalidItemName, String l_strComment, boolean l_blnIsComplete) 
    {
        final String STR_METHOD_NAME = " setInvalidItem(int, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("Index " + l_intIndex + " is not a legal index number.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("Index " + l_intIndex + " is not a legal index number.");
        }
        
        log.debug("\n�s�����ږ�: " + l_strInvalidItemName + "\n�R�����g: " + l_strComment + "\n�s���̏C�����������Ă��邩�̔��� " + l_blnIsComplete);
        this.accOpenInvalidValuesParams.setColumn("item_name" + l_intIndex, l_strInvalidItemName);
        this.accOpenInvalidValuesParams.setColumn("comment" + l_intIndex, l_strComment);
        this.accOpenInvalidValuesParams.setColumn("comp_flag" + l_intIndex, l_blnIsComplete ? BooleanEnum.TRUE : BooleanEnum.FALSE);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���l)<BR>
     * ���l�P�A�Q���Z�b�g����B<BR>
     * <BR>
     * this.�����J�ݕs���s.���l�P = ���l�P<BR>
     * this.�����J�ݕs���s.���l�Q = ���l�Q<BR>
     * @@param l_strNote1 - ���l�P
     * @@param l_strNote2 - ���l�Q
     * @@roseuid 41949948006F
     */
    public void setNote(String l_strNote1, String l_strNote2) 
    {
        final String STR_METHOD_NAME = " setNote(String, String)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("\n���l�P: " + l_strNote1 + "\n���l�Q: " + l_strNote2);
        this.accOpenInvalidValuesParams.setNote1(l_strNote1);
        this.accOpenInvalidValuesParams.setNote2(l_strNote2);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is�s�����ړo�^)<BR>
     * �w���index�ɊY������s�����ځi�s�����ږ��A�R�����g�A�����t���O�j��<BR>
     * �o�^����Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �|�w���index�ɑΉ�����s�����ږ��C�R�����g�������Ƃ�null�ł���΁A<BR>
     * �s�����ږ��o�^�Ɣ��肵false��ԋp����B<BR>
     * �|�w���index�ɑΉ�����s�����ږ��C�R�����g�̂ǂ��炩��null�łȂ��ꍇ�΁A<BR>
     * �s�����ړo�^�ς݂Ɣ��肵true��ԋp����B<BR>
     * <BR>
     * �� ����index�́A�s�����ڂ̗񖼂ɂȂ��Ă���ԍ��������B<BR>
     * �@@�Ⴆ�΁A�iindex=1�j�̏ꍇ�́A�ȉ��̔�����s���B<BR>
     * <BR>
     * �@@if (this.�����J�ݕs���s.�s�����ږ��P == null) && <BR>
     * (this.�����J�ݕs���s.�R�����g�P == null) {<BR>
     * �@@�@@return false;<BR>
     * �@@} else {<BR>
     * �@@�@@return true;<BR>
     * �@@}<BR>
     * @@param l_intIndex - index�i�s�����ڂ̔ԍ��j<BR>
     * <BR>
     * �� 1�`10�͈̔͂Ŏw�肷�邱��<BR>
     * 
     * @@return boolean
     * @@roseuid 4191BF6C0033
     */
    public boolean isInvalidItemRegist(int l_intIndex) 
    {
        final String STR_METHOD_NAME = " isInvalidItemRegist(int)";
        log.entering(STR_METHOD_NAME);

        if (l_intIndex < 1 || l_intIndex > 10)
        {
            log.debug("Index " + l_intIndex + " is not a legal index number.");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("Index " + l_intIndex + " is not a legal index number.");
        }

        if (this.accOpenInvalidValuesParams.getColumn("item_name" + l_intIndex) == null && this.accOpenInvalidValuesParams.getColumn("comment" + l_intIndex) == null)
        {
            log.debug("�w���index�ɑΉ�����s�����ږ��C�R�����g�������Ƃ�null�ł���΁A�s�����ږ��o�^�Ɣ��肵false��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.debug("�w���index�ɑΉ�����s�����ږ��C�R�����g�̂ǂ��炩��null�łȂ��ꍇ�΁A�s�����ړo�^�ς݂Ɣ��肵true��ԋp����B");
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (save�����J�ݕs��)<BR>
     * �����J�ݕs���e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �����J�ݕs���s�I�u�W�F�N�g�擾 <BR>
     * �@@�����J�ݕs��.getDataSourceObject()�ɂČ����J�ݕs���s���擾����B<BR> 
     * <BR>
     * �Q�j �X�V�����Z�b�g����B <BR>
     * �@@�����J�ݕs���s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@�X�V�҃R�[�h�F�@@�X�V�҃R�[�h<BR>
     * �@@�X�V�����F�@@TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * �R�j DB�X�V <BR>
     * �@@�����J�ݕs���s�I�u�W�F�N�g�̓��e�ŁA�����J�ݕs���e�[�u�����X�V����B<BR> 
     * �@@�،���ЃR�[�h�C���ʃR�[�h�ɊY���s�����ɑ��݂���ꍇ��Update�C<BR>
     * �@@���݂��Ȃ��ꍇ��Insert�ɂčX�V����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 4194867B008F
     */
    public void saveAccOpenInvalidValues(String l_strInstitutionCode, String l_strRequestNumber, String l_strUpdaterCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveAccOpenInvalidValues(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j �����J�ݕs���s�I�u�W�F�N�g�擾
        AccOpenInvalidValuesParams l_accOpenInvalidValuesParams = (AccOpenInvalidValuesParams)this.getDataSourceObject();
        
        //�Q�j �X�V�����Z�b�g����B
        Timestamp l_tsForSetting = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        l_accOpenInvalidValuesParams.setLastUpdater(l_strUpdaterCode);
        l_accOpenInvalidValuesParams.setLastUpdatedTimestamp(l_tsForSetting);
        
        try
        {
            //�R�j DB�X�V
            AccOpenInvalidValuesRow l_accOpenInvalidValuesRow = 
                AccOpenInvalidValuesDao.findRowByInstitutionCodeAccOpenRequestNumber(l_strInstitutionCode,
                l_strRequestNumber); //DataNetworkException, DataQueryException

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); //DataNetworkException, DataFindException
            
            if (l_accOpenInvalidValuesRow != null)
            {
                log.debug("�،���ЃR�[�h�C���ʃR�[�h�ɊY���s�����ɑ��݂���ꍇ��Update����B");
                l_queryProcessor.doUpdateQuery(l_accOpenInvalidValuesParams); //DataNetworkException, DataQueryException
            }
            else
            {
                l_accOpenInvalidValuesParams.setCreatedTimestamp(l_tsForSetting);
                log.debug("�،���ЃR�[�h�C���ʃR�[�h�ɊY���s�����݂��Ȃ��ꍇ��Insert�ɂčX�V����B");
                l_queryProcessor.doInsertQuery(l_accOpenInvalidValuesParams); //DataNetworkException, DataQueryException
            }
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
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return Object
     * @@roseuid 41B45E6B00EA
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.accOpenInvalidValuesParams;   
    }
}
@
