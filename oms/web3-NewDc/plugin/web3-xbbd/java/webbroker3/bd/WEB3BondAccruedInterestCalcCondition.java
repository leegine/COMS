head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAccruedInterestCalcCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�ߗ��q�v�Z����(WEB3BondAccruedInterestCalcCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 �s�p (���u) �V�K�쐬 
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.data.AccruedInterestCalcCondDao;
import webbroker3.bd.data.AccruedInterestCalcCondRow;


/**
 * (�o�ߗ��q�v�Z����)<BR>
 * �o�ߗ��q�v�Z����<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3BondAccruedInterestCalcCondition 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondAccruedInterestCalcCondition.class);   
    
    /**
     * (�o�ߗ��q�v�Z����Row)<BR>
     * �o�ߗ��q�v�Z�����s�I�u�W�F�N�g <BR>
     * �i��������DAO�N���X�j<BR>
     */
    private AccruedInterestCalcCondRow accruedInterestCalcCondRow;
    
    /**
     * @@roseuid 44FBFEE1037A
     */
    public WEB3BondAccruedInterestCalcCondition() 
    {
     
    }
    
    /**
     * (�o�ߗ��q�v�Z����)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �����̏����Ɉ�v����o�ߗ��q�v�Z�����I�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     * �@@�����̒l�ɂČo�ߗ��q�v�Z�����e�[�u������������B <BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@����.�o�ߗ��q�v�Z�^�C�v<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g <BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�o�ߗ��q�v�Z����Row�j�� <BR>
     * this.�o�ߗ��q�v�Z����Row�ɃZ�b�g����B <BR>
     * @@param l_strAccruedInterestCalcType - (�o�ߗ��q�v�Z�^�C�v)<BR>
     * �o�ߗ��q�v�Z�^�C�v<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44F394C90213
     */
    public WEB3BondAccruedInterestCalcCondition(String l_strAccruedInterestCalcType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " WEB3BondAccruedInterestCalcCondition(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@DB����  
            //�@@�����̒l�ɂČo�ߗ��q�v�Z�����e�[�u������������B  
            //�@@�@@[��������] 
            //�@@�@@����.�o�ߗ��q�v�Z�^�C�v 
            AccruedInterestCalcCondRow l_row = 
                AccruedInterestCalcCondDao.findRowByPk(l_strAccruedInterestCalcType);
            //DataFindException, DataNetworkException, DataQueryException
            
            //�Q�j�@@�s�I�u�W�F�N�g�Z�b�g  
            //�@@�������ʂ̍s�I�u�W�F�N�g�i�o�ߗ��q�v�Z����Row�j��  
            //this.�o�ߗ��q�v�Z����Row�ɃZ�b�g����B
            this.accruedInterestCalcCondRow = l_row;
            log.exiting(STR_METHOD_NAME);            
        }
        catch (DataFindException l_ex)
        {
            log.error("�o�ߗ��q�v�Z�����e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
    }
    
    /**
     * (get����敪)<BR>
     * �o�ߗ��q�v�Z����Row�������敪���擾����B<BR>
     * @@return String
     * @@roseuid 44F393130119
     */
    public String getBaseDateDiv() 
    {
        return this.accruedInterestCalcCondRow.getBaseDateDiv();
    }
    
    /**
     * (get������敪)<BR>
     * �o�ߗ��q�v�Z����Row���������敪���擾����B<BR>
     * @@return String
     * @@roseuid 44F393540281
     */
    public String getBaseDaysDiv() 
    {
        return this.accruedInterestCalcCondRow.getBaseDaysDiv();
    }
    
    /**
     * (get�o�ߓ����敪)<BR>
     * �o�ߗ��q�v�Z����Row����o�ߓ����敪���擾����B<BR>
     * @@return String
     * @@roseuid 44F393670158
     */
    public String getElapsedDaysDiv() 
    {
        return this.accruedInterestCalcCondRow.getElapsedDaysDiv();
    }
    
    /**
     * (get�v�Z�����)<BR>
     * �o�ߗ��q�v�Z����Row����v�Z��������擾����B<BR>
     * @@return String
     * @@roseuid 44F39375031D
     */
    public String getCalcBaseForm() 
    {
        return this.accruedInterestCalcCondRow.getCalcBaseForm();
    }
    
    /**
     * (get�P������)<BR>
     * �o�ߗ��q�v�Z����Row����P���������擾����B<BR>
     * @@return int
     * @@roseuid 44F393930177
     */
    public int getUnitPriceScale() 
    {
        return this.accruedInterestCalcCondRow.getUnitPriceScale();
    }
    
    /**
     * (get�P�ʖ��������敪)<BR>
     * �o�ߗ��q�v�Z����Row����P�ʖ��������敪���擾����B<BR>
     * @@return String
     * @@roseuid 44F394630196
     */
    public String getUnitRoundDiv() 
    {
        return this.accruedInterestCalcCondRow.getUnitRoundDiv();
    }
    
    /**
     * (get���z���������敪)<BR>
     * �o�ߗ��q�v�Z����Row������z���������敪���擾����B<BR>
     * @@return String
     * @@roseuid 44F394890223
     */
    public String getAmountRoundDiv() 
    {
        return this.accruedInterestCalcCondRow.getAmountRoundDiv();
    }
    
    /**
     * (get�o�ߗ��q�v�Z�^�C�v )<BR>
     * �o�ߗ��q�v�Z����Row����o�ߗ��q�v�Z�^�C�v���擾����B
     * @@return String
     */
    public String getAccruedInterestCalcType()
    {
        return this.accruedInterestCalcCondRow.getAccruedInterestCalcType();
    }
}
@
