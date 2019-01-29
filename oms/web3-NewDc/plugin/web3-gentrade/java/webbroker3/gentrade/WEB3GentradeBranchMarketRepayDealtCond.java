head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBranchMarketRepayDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i���X�s��ٍϕʁj�戵����(WEB3GentradeBranchMarketRepayDealtCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/13 羐� (���u) �V�K�쐬
Revesion History : 2004/08/31 �Г� (���u) get�戵�\�ٍ�()���폜
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondDao;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���X�s��ٍϕʁj�戵����<BR>
 * �،���ЁA���X�A�s��A�ٍϋ敪�A�ٍϊ����l�� <BR>
 * �̎戵�\������\������B <BR>
 * �M�p������Ŏg�p�B<BR>
 * <BR>
 * �iDB���C�A�E�g �u�i���X�s��ٍϕʁj�戵����.xls�v�Q�Ɓj<BR>
 */
public class WEB3GentradeBranchMarketRepayDealtCond implements BusinessObject
{
    
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranchMarketRepayDealtCond.class);

    /**
     * �i���X�s��ٍϕʁj�戵����Row�s�I�u�W�F�N�g<BR>
     * �iDAO���������N���X�j<BR>
     */
    private BranchMarketRepayDealtCondRow branchMarketRepayDealtCondRow;    

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A<BR>
     * �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_branchMarketRepayDealtCondRow - 
     * �i���X�s��ٍϕʁj�戵�����E�s�I�u�W�F�N�g<BR>
     * @@return WEB3GentradeBranchMarketRepayDealtCond
     * @@roseuid 40B137CA0231
     */
    public WEB3GentradeBranchMarketRepayDealtCond(BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow)
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBranchMarketRepayDealtCond(BranchMarketRepayDealtCondRow)";
        if(l_branchMarketRepayDealtCondRow == null)
        {
            log.error("�i���X�s��ٍϕʁj�戵�����E�s�I�u�W�F�N�g = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�i���X�s��ٍϕʁj�戵�����E�s�I�u�W�F�N�g = null");
        }
        this.branchMarketRepayDealtCondRow = l_branchMarketRepayDealtCondRow;

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �����̏����Ɉ�v����i���X�s��ٍϕʁj�戵���� <BR>
     * �I�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     * �@@�����̒l�ɂāi���X�s��ٍϕʁj�戵�����e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�i���X�s��ٍϕʁj�戵����Row�j��<BR>
     *   this.�i���X�s��ٍϕʁj�戵�����ɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@param l_strRepaymentDiv - �ٍϋ敪<BR>
     *     1�F���x�M�p  2�F��ʐM�p <BR>
     * @@param l_dbRepaymentNum - �ٍϊ����l<BR>
     * 
     * @@return WEB3GentradeBranchMarketRepayDealtCond
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B137CA01F3
     */
    public WEB3GentradeBranchMarketRepayDealtCond(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBranchMarketRepayDealtCond(String,String,String,String,double)";
        log.entering(STR_METHOD_NAME);

        long l_lngRepaymentNum = (long)l_dbRepaymentNum;
        int l_intRepaymentNum = Integer.parseInt(Long.toString(l_lngRepaymentNum));
        BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow;

        try
        {
            l_branchMarketRepayDealtCondRow = 
                BranchMarketRepayDealtCondDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strMarketCode,
                    l_strRepaymentDiv,
                    l_intRepaymentNum);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        this.branchMarketRepayDealtCondRow = l_branchMarketRepayDealtCondRow;
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * this.���X�s��ٍϕʎ戵����Row��ԋp����B<BR>
     * @@return Object
     * @@roseuid 40B137CA01E3
     */
    public Object getDataSourceObject()
    {
        return this.branchMarketRepayDealtCondRow;
    }

    /**
     * (is�戵�\)<BR>
     * �戵�\�ł��邩��ԋp����B<BR>
     * <BR>
     * this.�i���X�s��ٍϕʁj�戵����Row�̃v���p�e�B�u�戵�\�v���A<BR>
     * �h�戵�\�h�ł����true�A�ȊOfalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40B137CA0203
     */
    public boolean isHandlingPossible()
    {
        if(WEB3DealtDef.CAN_DEALT.equals(this.branchMarketRepayDealtCondRow.getMartCanDealt()))
        {
            return true;
        }
        return false;
    }

    /**
     * (get�������x�P��)<BR>
     * �����w��l�ɉ����āA�������x�P�ʂ��擾����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ蔻�肵�A���x�P�ʒl��ԋp����B<BR>
     * <BR>
     * ��������is�V�K����true�i�V�K���j�@@���@@<BR>
     *    ������is������true�i���j�̏ꍇ <BR>
     * �@@  �|�����̏��敪���ꕔ���̏ꍇ�́A<BR>
     *       �h�V�K�������x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * �@@  �|�����̏��敪���񕔏��̏ꍇ�́A�h�V�K <BR>
     *        �������x�P�ʁi�񕔏��j�h��ԋp����B<BR>
     * �@@  �|�����̏��敪����L�ȊO�̏ꍇ�́A�h�V�K <BR>
     *        �������x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * <BR>
     * ��������is�V�K����true�i�V�K���j�@@���@@<BR>
     *    ������is������false�i���j�̏ꍇ<BR>
     * �@@  �|�����̏��敪���ꕔ���̏ꍇ�́A�h�V�K <BR>
     *        �������x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * �@@  �|�����̏��敪���񕔏��̏ꍇ�́A�h�V�K���� <BR>
     *        ���x�P�ʁi�񕔏��j�h��ԋp����B<BR>
     * �@@  �|�����̏��敪����L�ȊO�̏ꍇ�́A�h�V�K���� <BR>
     *        ���x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * <BR>
     * ��������is�V�K����false�i�ԍρj�@@���@@<BR>
     *    ������is������true�i���j�̏ꍇ <BR>
     * �@@  �|�����̏��敪���ꕔ���̏ꍇ�́A�h���� <BR>
     *        �ԍό��x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * �@@  �|�����̏��敪���񕔏��̏ꍇ�́A�h���� <BR>
     *        �ԍό��x�P�ʁi�񕔏��j�h��ԋp����B<BR>
     * �@@  �|�����̏��敪����L�ȊO�̏ꍇ�́A�h�����ԍ� <BR>
     *        ���x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * <BR>
     * ��������is�V�K����false�i�ԍρj�@@���@@<BR>
     *    ������is������false�i���j�̏ꍇ <BR>
     * �@@  �|�����̏��敪���ꕔ���̏ꍇ�́A�h���� <BR>
     *        �ԍό��x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * �@@  �|�����̏��敪���񕔏��̏ꍇ�́A�h���� <BR>
     *        �ԍό��x�P�ʁi�񕔏��j�h��ԋp����B<BR>
     * �@@  �|�����̏��敪����L�ȊO�̏ꍇ�́A�h���� <BR>
     *        �ԍό��x�P�ʁi�ꕔ���j�h��ԋp����B<BR>
     * @@param l_strListingDivision - ���敪<BR>
     *    ������������}�X�^�ɂĒ�`����Ă�����敪�B<BR>
     * @@param l_blnIsOpenContract - is�V�K��<BR>
     *    �V�K���������ǂ����𔻕ʂ���t���O�B<BR>
     *    �V�K���̏ꍇ��true�A�ԍς̏ꍇ��false�B<BR>
     * @@param l_blnIsBuyOrder - is����<BR>
     *    �����^�����𔻕ʂ���t���O�B<BR>
     *    �����̏ꍇ��true�A�����̏ꍇ��false�B<BR>
     * 
     * @@return double
     * @@roseuid 40B137CA0222
     */
    public double getDealingLimitUnit(
        String l_strListingDivision,
        boolean l_blnIsOpenContract,
        boolean l_blnIsBuyOrder)
    {
        final String STR_METHOD_NAME = "getDealingLimitUnit(String,boolean,boolean)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblDealingLimitUnit = 0;
        
        //��������is�V�K����true�i�V�K���j�@@���@@������is������true�i���j�̏ꍇ
        if(l_blnIsOpenContract && l_blnIsBuyOrder)
        {
            if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
            {
                //�|�����̏��敪���ꕔ���̏ꍇ�́A
                //�h�V�K�������x�P�ʁi�ꕔ���j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMLong1stSec();
            }
            else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
            {
                //�|�����̏��敪���񕔏��̏ꍇ�́A�h�V�K 
                //�������x�P�ʁi�񕔏��j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMLong2ndSec();
                
            }
            else
            {
                //�|�����̏��敪����L�ȊO�̏ꍇ�́A�h�V�K 
                //�������x�P�ʁi�ꕔ���j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMLong1stSec();
            }  
        }
        //��������is�V�K����true�i�V�K���j�@@���@@������is������false�i���j�̏ꍇ
        else if(l_blnIsOpenContract && ( ! l_blnIsBuyOrder))
        {         
            if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
            {
                //�|�����̏��敪���ꕔ���̏ꍇ�́A�h�V�K 
                //�������x�P�ʁi�ꕔ���j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMShort1stSec(); 
            }
            else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
            {
                //�|�����̏��敪���񕔏��̏ꍇ�́A�h�V�K���� 
                //���x�P�ʁi�񕔏��j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMShort2ndSec(); 
            }
            else
            {
                //�|�����̏��敪����L�ȊO�̏ꍇ�́A�h�V�K���� 
                //���x�P�ʁi�ꕔ���j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMShort1stSec(); 
            } 
        }
        //��������is�V�K����false�i�ԍρj�@@���@@������is������true�i���j�̏ꍇ
        else if(( ! l_blnIsOpenContract) && l_blnIsBuyOrder)
        {            
            if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
            {
                //�|�����̏��敪���ꕔ���̏ꍇ�́A�h���� 
                //�ԍό��x�P�ʁi�ꕔ���j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmLong1stSec(); 
            }
            else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
            {
                //�|�����̏��敪���񕔏��̏ꍇ�́A�h���� 
                //�ԍό��x�P�ʁi�񕔏��j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmLong2ndSec(); 
            }
            else
            {
                //�|�����̏��敪����L�ȊO�̏ꍇ�́A�h�����ԍ� 
                //���x�P�ʁi�ꕔ���j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmLong1stSec(); 
            }
            
        }
        //��������is�V�K����false�i�ԍρj�@@���@@������is������false�i���j�̏ꍇ
        else  if(( ! l_blnIsOpenContract) && ( ! l_blnIsBuyOrder))
        {
            if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
            {
                //�|�����̏��敪���ꕔ���̏ꍇ�́A�h���� 
                //�ԍό��x�P�ʁi�ꕔ���j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmShort1stSec(); 
            }
            else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
            {
                //�|�����̏��敪���񕔏��̏ꍇ�́A�h���� 
                //�ԍό��x�P�ʁi�񕔏��j�h��ԋp����B
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmShort2ndSec(); 
            }
            else
            {
                //�|�����̏��敪����L�ȊO�̏ꍇ�́A�h���� 
                //�ԍό��x�P�ʁi�ꕔ���j�h��ԋp����
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmShort1stSec(); 
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblDealingLimitUnit;
    }

    /**
     * �istatic���\�b�h�j<BR>
     * get�i���X�s��ٍϕʁj�戵����<BR>
     * <BR>
     * �����̕��X�ɊY������i���X�s��ٍϕʁj�戵����<BR>
     * �I�u�W�F�N�g��S�Ď擾����B<BR>
     * ���s��R�[�h�A�ٍϋ敪�A�ٍϊ����l �����Ŏ擾����B<BR>
     * <BR>
     * �P�jDB���� <BR>
     * �@@�i���X�s��ٍϕʁj�戵�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ���X.getInstitution().getInstitutionCode()<BR>
     * �@@���X�R�[�h = ���X.getBranchCode()<BR>
     * �@@���s��R�[�h�A�ٍϋ敪�A�ٍϊ����l �����Ń\�[�g���Ď擾����B<BR>
     * <BR>
     * �Q�j�I�u�W�F�N�g���� <BR>
     * �@@�������ʂ́i���X�s��ٍϕʁj�戵�����s�I�u�W�F�N�g���w�肵�A<BR>
     * �@@�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B<BR>
     * <BR>
     * @@param l_genBranch - ���X�I�u�W�F�N�g<BR>
     * @@return WEB3GentradeBranchMarketRepayDealtCond[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B137CA0241
     */
    public static WEB3GentradeBranchMarketRepayDealtCond[] getBranchMarketRepayDealtCond(
        WEB3GentradeBranch l_genBranch)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getBranchMarketRepayDealtCond(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //* �@@�i���X�s��ٍϕʁj�戵�����e�[�u�����ȉ��̏����Ō�������B
        //* �@@[����]
        //* �@@�،���ЃR�[�h = ���X.getInstitution().getInstitutionCode()
        //* �@@���X�R�[�h = ���X.getBranchCode()
        //* �@@���s��R�[�h�A�ٍϋ敪�A�ٍϊ����l �����Ń\�[�g���Ď擾����B
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        String l_strOrderBy = "to_number(market_code), repayment_div, repayment_num ";
        Object[] l_objWhere =
        {
            l_genBranch.getInstitution().getInstitutionCode(),
            l_genBranch.getBranchCode()
        };
        
        List l_lisRecords;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                BranchMarketRepayDealtCondRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeBranchMarketRepayDealtCond.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j�I�u�W�F�N�g���� 
        //�������ʂ́i���X�s��ٍϕʁj�戵�����s�I�u�W�F�N�g���w�肵�A
        //�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g�𐶐�����B
        //���������I�u�W�F�N�g��z��ŕԋp����B
        int l_intSize  = l_lisRecords.size();
        WEB3GentradeBranchMarketRepayDealtCond[] l_genBranchMarketRepayDealtConds = 
            new WEB3GentradeBranchMarketRepayDealtCond[l_intSize];
        for(int i = 0; i < l_intSize; i++)
        {
            l_genBranchMarketRepayDealtConds[i] = 
                new WEB3GentradeBranchMarketRepayDealtCond((BranchMarketRepayDealtCondRow)l_lisRecords.get(i));
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_genBranchMarketRepayDealtConds;
    }

    /**
     * (get�s��R�[�h)<BR>
     * �{�I�u�W�F�N�g���ێ�����s��R�[�h���擾����B<BR>
     * <BR>
     * this.�i���X�s��ٍϕʁj�戵����Row.�s��R�[�h��ԋp����B<BR>
     * @@return java.lang.String
     * @@roseuid 40B137CA0260
     */
    public String getMarketCode()
    {
        return this.branchMarketRepayDealtCondRow.getMarketCode();
    }

    /**
     * �istatic���\�b�h�j<BR>
     * (get�戵�\�s��)<BR>
     * �����̕��X�ɊY������i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g<BR>
     * ��S�Ď擾���A �����ٍ̕ϋ敪(*)�A�ٍϊ����l(*)�ɊY������<BR>
     * �I�u�W�F�N�g�̎s��R�[�h�̔z���ԋp����B<BR> 
     * ���z��̏����́A�s��R�[�h�����Ƃ���B<BR>
     *  <BR>
     * (*)�ٍϋ敪�A�ٍϊ����l�͎w�莞�̂݃`�F�b�N���e�ɒǉ�����B<BR>
     *    �ٍϊ����l�݂̂̎w��͕s�B<BR>
     *  <BR>
     * �P�j�����`�F�b�N <BR>
     * ����.�ٍϋ敪==�hDEFAULT�i�w��Ȃ��j�h�A���A<BR>
     * ����.�ٍϊ����l!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ�A<BR> 
     * �u�����w��G���[�v�̗�O��throw����B<BR>
     * class    : WEB3BaseRuntimeException<BR>
     * tag      : SYSTEM_ERROR_80017<BR> 
     *  <BR> 
     * �Q�j�f�[�^�擾 <BR> 
     * this.get�i���X�s��ٍϕʁj�戵����(���X)�ɂ��A<BR>  
     * �����̕��X�ɊY������i���X�s��ٍϕʁj�戵���� <BR> 
     * �I�u�W�F�N�g��S�Ď擾����B <BR>
     *  <BR>
     * �R�jArrayList�𐶐�����B<BR>
     *  <BR>
     * �S�j�戵�\�`�F�b�N <BR>
     *   �Q�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���s��R�[�h<BR>
     *   ��ArrayList�ɒǉ�����B <BR>
     *  <BR>
     * [�`�F�b�N���e] <BR>
     * ---------------------------------------------------<BR> 
     * �S�|�P�jthis.is�戵�\() == true�ł��邱�ƁB <BR>
     *  <BR>
     * �S�|�Q�j�ȉ��̏����ɂ��`�F�b�N�𕪊򂷂�B <BR>
     *    [�p�����[�^.�ٍϋ敪!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ] <BR>
     *      �i���X�s��ٍϕʁj�戵����.�ٍϋ敪 != �p�����[�^.�ٍϋ敪��<BR>
     *      �ꍇ�A���̃I�u�W�F�N�g�֏������ڍs����B(continue) <BR> 
     *    [�p�����[�^.�ٍϊ����l!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ] <BR>
     *      �i���X�s��ٍϕʁj�戵����.�ٍϊ����l != �p�����[�^.�ٍϊ����l<BR>
     *      �̏ꍇ�A���̃I�u�W�F�N�g�֏������ڍs����B(continue) <BR>
     * ----------------------------------------------------<BR> 
     *  <BR>
     * �S�|�R�j��L�`�F�b�N��ʉ߂����I�u�W�F�N�g�ɂ��āA�ȉ��̏����� <BR>
     * ���{����B��������ArrayList�Ɏs��R�[�h��ǉ�����B<BR> 
     * ��ArrayList�ɒǉ�����s��R�[�h�́A�d�����Ȃ����݂̂̂��Z�b�g���邱�ƁB<BR> 
     *  <BR>
     * �T�j�쐬����ArrayList.toArray()�̌��ʂ�ԋp����B <BR> 
     *  <BR> 
     * @@param l_genBranch - ���X�I�u�W�F�N�g<BR>
     * @@param l_strRepaymentDiv - �ٍϋ敪<BR>
     *   (WEB3GentradeRepaymentDivDef�ɂĒ�`) <BR>
     * @@param l_dbRepaymentNum - �ٍϊ����l<BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B137CA0270
     */
    public static String[] getHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getHandlingPossibleMarket(WEB3GentradeBranch, String, double)";
        log.entering(STR_METHOD_NAME);
     
        //�P�j�����`�F�b�N 
        //* ����.�ٍϋ敪==�hDEFAULT�i�w��Ȃ��j�h�A���A
        //* ����.�ٍϊ����l!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ�A
        //* �u�����w��G���[�v�̗�O��throw����
        if(WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv) 
           && (Double.compare(l_dbRepaymentNum,0) != 0))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeBranchMarketRepayDealtCond.class.getName()
                    + "." + STR_METHOD_NAME,
                "����.�ٍϋ敪 = " + l_strRepaymentDiv 
                    + ", ����.�ٍϊ����l = " + l_dbRepaymentNum);
        }
        
        //�Q�j�f�[�^�擾
        //this.get�i���X�s��ٍϕʁj�戵����(���X)�ɂ��A
        //�����̕��X�ɊY������i���X�s��ٍϕʁj�戵����
        //�I�u�W�F�N�g��S�Ď擾����B
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketRepayDealtConds = 
            getBranchMarketRepayDealtCond(l_genBranch);
        
        //�S�j�戵�\�`�F�b�N
        WEB3GentradeBranchMarketRepayDealtCond l_tmpBranchMarketRepayDealtCond;
        BranchMarketRepayDealtCondRow l_tmpBranchMarketRepayDealtCondRow;
        String l_strTmpMarketCode;
        ArrayList l_lstHandlingPossibleMarkets = new ArrayList();
        int l_intSize  = l_branchMarketRepayDealtConds.length;
        for(int i = 0; i<l_intSize; i++ )
        {
            //get�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g
            l_tmpBranchMarketRepayDealtCond = l_branchMarketRepayDealtConds[i];
            l_tmpBranchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)
                l_tmpBranchMarketRepayDealtCond.getDataSourceObject();           
            
            //�S�|�P�jthis.is�戵�\() == true�ł��邱��
            if(l_tmpBranchMarketRepayDealtCond.isHandlingPossible())
            {
                //�S�|�Q�j�ȉ��̏����ɂ��`�F�b�N�𕪊򂷂�B 
                //*    [�p�����[�^.�ٍϋ敪!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ] 
                //*      �i���X�s��ٍϕʁj�戵����.�ٍϋ敪 != �p�����[�^.�ٍϋ敪��
                //*      �ꍇ�A���̃I�u�W�F�N�g�֏������ڍs����B(continue) 
                //*    [�p�����[�^.�ٍϊ����l!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ] 
                //*      �i���X�s��ٍϕʁj�戵����.�ٍϊ����l != �p�����[�^.�ٍϊ����l
                //*      �̏ꍇ�A���̃I�u�W�F�N�g�֏������ڍs����B(continue) 
                if( ! WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv))
                {
                    if( ! l_strRepaymentDiv.equals(l_tmpBranchMarketRepayDealtCondRow.getRepaymentDiv()))
                    {
                        continue;
                    }
                }
                if(Double.compare(l_dbRepaymentNum,0) != 0)
                {
                    if(Double.compare(l_dbRepaymentNum,l_tmpBranchMarketRepayDealtCondRow.getRepaymentNum()) != 0)
                    {
                        continue;
                    }
                }
                
                //�S�|�R�j��������ArrayList�Ɏs��R�[�h��ǉ�����
                //ArrayList�ɒǉ�����s��R�[�h�́A�d�����Ȃ����݂̂̂��Z�b�g���邱�ƁB
                l_strTmpMarketCode = l_tmpBranchMarketRepayDealtCond.getMarketCode();
                if( ! l_lstHandlingPossibleMarkets.contains(l_strTmpMarketCode))
                {
                    l_lstHandlingPossibleMarkets.add(l_strTmpMarketCode);
                }
                
            }
        }
        
        //�T�j�쐬����ArrayList��z�񂵂ĕԂ��B
        l_intSize = l_lstHandlingPossibleMarkets.size();
        String[] l_strMarketCodes = new String[l_intSize];
        for(int i = 0; i<l_intSize; i++ )
        {
            l_strMarketCodes[i] = (String)l_lstHandlingPossibleMarkets.get(i);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
    }
    
    /**
     *  (get������戵�\�s��)<BR>
     * �istatic���\�b�h�j<BR>
     * �����̕��X�ɊY������i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g��<BR>
     * �S�Ď擾���A�����ٍ̕ϋ敪(*)�A�ٍϊ����l(*)�ɊY������<BR>
     * �I�u�W�F�N�g�̎s��R�[�h�̂����A������̎s��̎s��R�[�h�݂̂�<BR>
     * �z���ԋp����B<BR>
     * ���z��̏����́A�s��R�[�h�����Ƃ���B<BR>
     *  <BR>
     * (*)�ٍϋ敪�A�ٍϊ����l�͎w�莞�̂݃`�F�b�N���e�ɒǉ�����B<BR>
     * �ٍϊ����l�݂̂̎w��͕s�B<BR>
     *  <BR>
     * �P�j�����`�F�b�N<BR>
     * ����.�ٍϋ敪==�hDEFAULT�i�w��Ȃ��j�h�A���A<BR>
     * ����.�ٍϊ����l!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ�A<BR>
     * �u�����w��G���[�v�̗�O��throw����B<BR>
     *  <BR>
     * �Q�j�f�[�^�擾<BR>
     * this.get�i���X�s��ٍϕʁj�戵����(���X)�ɂ��A�����̕��X��<BR>
     * �Y������i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     *  <BR>
     * �R�jArrayList�𐶐�����B<BR>
     *  <BR>
     * �S�j�戵�\�`�F�b�N<BR>
     *  �Q�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���s��R�[�h��<BR>
     *  ArrayList�ɒǉ�����B<BR>
     *  <BR>
     * [�`�F�b�N���e]
     * ----------------------------------------------------<BR>
     * �S�|�P�jthis.is�戵�\() == true�ł��邱�ƁB<BR>
     *  <BR>
     * �S�|�Q�j�ȉ��̏����ɂ��`�F�b�N�𕪊򂷂�B<BR>
     *   [�p�����[�^.�ٍϋ敪!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ]<BR>
     *      �i���X�s��ٍϕʁj�戵����.�ٍϋ敪 != �p�����[�^.�ٍϋ敪<BR>
     *       �̏ꍇ�A���̃I�u�W�F�N�g�֏������ڍs����B(continue)<BR>
     *  <BR>
     *   [�p�����[�^.�ٍϊ����l!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ]<BR>
     *      �i���X�s��ٍϕʁj�戵����.�ٍϊ����l != �p�����[�^.�ٍϊ����l<BR>
     *       �̏ꍇ�A���̃I�u�W�F�N�g�֏������ڍs����B(continue)<BR>
     *  <BR>
     * �S�|�R�j������̎s��(*1)�ł��邱�ƁB<BR>
     *  <BR>
     * (*1)������̎s��<BR>
     * �g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��(���X.�،���ЃR�[�h, <BR>
     * ���Y���R�[�h�̎s��R�[�h)�ɂ��s��I�u�W�F�N�g���擾�B�擾����<BR>
     * �s��.�����~��"�����~��"�̏ꍇ�́A������̎s��Ɣ��肷��B<BR>
     * ���s��I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A������̎s��ł͂Ȃ���<BR>
     * ���肷��B<BR>
     * ----------------------------------------------------<BR>
     *  <BR>
     * �S�|�S�j��L�`�F�b�N��ʉ߂����I�u�W�F�N�g�ɂ��āA�ȉ��̏�����<BR>
     * ���{����B��������ArrayList�Ɏs��R�[�h��ǉ�����B<BR>
     * ��ArrayList�ɒǉ�����s��R�[�h�́A�d�����Ȃ����݂̂̂��Z�b�g���邱��<BR>
     *  <BR>
     * �T�j�쐬����ArrayList.toArray()�̌��ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_genBranch - ���X�I�u�W�F�N�g<BR>
     * @@param l_strRepaymentDiv - �ٍϋ敪<BR>
     *   (WEB3GentradeRepaymentDivDef�ɂĒ�`) <BR>
     * @@param l_dbRepaymentNum - �ٍϊ����l<BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     */
    public static String[] getTradingHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getTradingHandlingPossibleMarket(WEB3GentradeBranch, String, double)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_genFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //�P�j�����`�F�b�N 
        //* ����.�ٍϋ敪==�hDEFAULT�i�w��Ȃ��j�h�A���A
        //* ����.�ٍϊ����l!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ�A
        //* �u�����w��G���[�v�̗�O��throw����
        if(WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv) 
           && (Double.compare(l_dbRepaymentNum,0) != 0))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeBranchMarketRepayDealtCond.class.getName()
                    + "." + STR_METHOD_NAME,
                "����.�ٍϋ敪 = " + l_strRepaymentDiv 
                    + ", ����.�ٍϊ����l = " + l_dbRepaymentNum);
        }
        
        //�Q�j�f�[�^�擾
        //this.get�i���X�s��ٍϕʁj�戵����(���X)�ɂ��A
        //�����̕��X�ɊY������i���X�s��ٍϕʁj�戵����
        //�I�u�W�F�N�g��S�Ď擾����B
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketRepayDealtConds = 
            getBranchMarketRepayDealtCond(l_genBranch);
        
        //�S�j�戵�\�`�F�b�N
        //    �Q�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���s��R�[�h��ArrayList�ɒǉ�����B
        WEB3GentradeBranchMarketRepayDealtCond l_tmpBranchMarketRepayDealtCond;
        BranchMarketRepayDealtCondRow l_tmpBranchMarketRepayDealtCondRow;
        String l_strTmpMarketCode;
        ArrayList l_lstHandlingPossibleMarkets = new ArrayList();
        int l_intSize  = l_branchMarketRepayDealtConds.length;
        for(int i = 0; i<l_intSize; i++ )
        {
            //get�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g
            l_tmpBranchMarketRepayDealtCond = l_branchMarketRepayDealtConds[i];
            l_tmpBranchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)
                l_tmpBranchMarketRepayDealtCond.getDataSourceObject();  
                
            //�S�|�P�jthis.is�戵�\() == true�ł��邱��
            if(l_tmpBranchMarketRepayDealtCond.isHandlingPossible() == false)
            {
                continue;
            }
            
            //�S�|�Q�j�ȉ��̏����ɂ��`�F�b�N�𕪊򂷂�B 
            //*    [�p�����[�^.�ٍϋ敪!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ] 
            //*      �i���X�s��ٍϕʁj�戵����.�ٍϋ敪 != �p�����[�^.�ٍϋ敪��
            //*      �ꍇ�A���̃I�u�W�F�N�g�֏������ڍs����B(continue) 
            //*    [�p�����[�^.�ٍϊ����l!=�hDEFAULT�i�w��Ȃ��j�h�̏ꍇ] 
            //*      �i���X�s��ٍϕʁj�戵����.�ٍϊ����l != �p�����[�^.�ٍϊ����l
            //*      �̏ꍇ�A���̃I�u�W�F�N�g�֏������ڍs����B(continue) 
            if( ! WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv))
            {
                if( ! l_strRepaymentDiv.equals(l_tmpBranchMarketRepayDealtCondRow.getRepaymentDiv()))
                {
                    continue;
                }
            }
            if(Double.compare(l_dbRepaymentNum,0) != 0)
            {
                if(Double.compare(l_dbRepaymentNum,l_tmpBranchMarketRepayDealtCondRow.getRepaymentNum()) != 0)
                {
                    continue;
                }
            }
            
            //�S�|�R�j������̎s��(*1)�ł��邱�ƁB
            //(*1)������̎s��
            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��(���X.�،���ЃR�[�h, 
            //���Y���R�[�h�̎s��R�[�h)�ɂ��s��I�u�W�F�N�g���擾�B�擾����
            //�s��.�����~��"�����~��"�̏ꍇ�́A������̎s��Ɣ��肷��B
            //���s��I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A������̎s��ł͂Ȃ��Ɣ��肷��
            Market l_market = null;
            try
            {
                l_market =
                    l_genFinObjectManager.getMarket(
                        l_genBranch.getInstitution().getInstitutionCode(),
                        l_tmpBranchMarketRepayDealtCond.getMarketCode());
            }
            catch (NotFoundException nfe)
            {
                log.debug("�s��I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A������̎s��ł͂Ȃ��Ɣ��肷��");
            }
            boolean l_isTradingMarket = false;
            if(l_market != null)
            {
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                if(WEB3SuspensionDef.SUSPENSION.equals(l_marketRow.getSuspension()) == false)
                {
                    l_isTradingMarket = true;
                }
            }
            if(l_isTradingMarket == false)
            {
                continue;
            }
            
            //�S�|�S�j��������ArrayList�Ɏs��R�[�h��ǉ�����
            //ArrayList�ɒǉ�����s��R�[�h�́A�d�����Ȃ����݂̂̂��Z�b�g���邱�ƁB
            l_strTmpMarketCode = l_tmpBranchMarketRepayDealtCond.getMarketCode();
            if( ! l_lstHandlingPossibleMarkets.contains(l_strTmpMarketCode))
            {
                l_lstHandlingPossibleMarkets.add(l_strTmpMarketCode);
            }
            
        }
        
        //�T�j�쐬����ArrayList��z�񂵂ĕԂ��B
        l_intSize = l_lstHandlingPossibleMarkets.size();
        String[] l_strMarketCodes = new String[l_intSize];
        for(int i = 0; i<l_intSize; i++ )
        {
            l_strMarketCodes[i] = (String)l_lstHandlingPossibleMarkets.get(i);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
        
    }
    
}
@
