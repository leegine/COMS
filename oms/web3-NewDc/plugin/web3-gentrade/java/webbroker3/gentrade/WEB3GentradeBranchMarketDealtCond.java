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
filename	WEB3GentradeBranchMarketDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (���X�s���)�戵����(WEB3GentradeBranchMarketDealtCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 �Ή� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.gentrade.data.BranchMarketDealtCondDao;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (���X�s��ʁj�戵���� <BR>
 * �،���ЁA���X�A�s�ꖈ�̎戵�\����������\������B <BR>
 * ���������Ŏg�p�B <BR>
 * <BR>
 * �iDB���C�A�E�g �u�i���X�s��ʁj�戵�����e�[�u��.xls�v�Q�Ɓj <BR>
 * <BR>
 */
public class WEB3GentradeBranchMarketDealtCond implements BusinessObject
{

    /**
     * �i���X�s��ʁj�戵����Row�s�I�u�W�F�N�g
     * �iDAO���������N���X�j
     */
    private BranchMarketDealtCondRow branchMarketDealtCondRow;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranchMarketDealtCond.class);

    /**
     * �R���X�g���N�^�B <BR>
     * �i���X�s��ʁj�戵���� <BR>
     * 
     * �{�I�u�W�F�N�g���C���X�^���X�����A <BR>
     * �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_branchMarketDealtCondRow - �i���X�s��ʁj�戵�����s�I�u�W�F�N�g
     * @@roseuid 40640F0102AE
     */
    public WEB3GentradeBranchMarketDealtCond(BranchMarketDealtCondRow l_branchMarketDealtCondRow)
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBranchMarketDealtCond(BranchMarketDealtCondRow)";
        if(l_branchMarketDealtCondRow == null)
        {
            log.error("�i���X�s��ʁj�戵�����s�I�u�W�F�N�g = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�i���X�s��ʁj�戵�����s�I�u�W�F�N�g = null");
        }
        
        this.branchMarketDealtCondRow = l_branchMarketDealtCondRow;
    }

    /**
     * �R���X�g���N�^�B
     * �����̏����Ɉ�v����i���X�s��ʁj�戵�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     * �@@�����̒l�ɂāi���X�s��ʁj�戵�����e�[�u������������B <BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g <BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�i���X�s��ʁj�戵����Row�j�� <BR>
     * this.�i���X�s��ʁj�戵�����ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h <BR>
     * @@param l_strBranchCode - ���X�R�[�h <BR>
     * @@param l_strMarketCode - �s��R�[�h <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 4060ED5C0373
     */
    public WEB3GentradeBranchMarketDealtCond(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeHandingCondBranchMarket(String,String,String)";
        log.entering(STR_METHOD_NAME);

        BranchMarketDealtCondRow l_row = null;
        try
        {
            //�i���X�s��ʁj�戵�����e�[�u��
            l_row = BranchMarketDealtCondDao.findRowByPk(
                 l_strInstitutionCode,
                 l_strBranchCode,
                 l_strMarketCode
                 );
        }
        catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        if (l_row != null)
        {
            this.branchMarketDealtCondRow = l_row;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.���X�s��ʎ戵����Row��ԋp����B
     * @@return Object
     * @@roseuid 4060ED5C0372
     */
    public Object getDataSourceObject()
    {
        return this.branchMarketDealtCondRow;
    }

    /**
     * (is�戵�\) <BR>
     * �w�菤�i���戵�\�ł��邩��ԋp����B <BR>
     * <BR>
     * �����̏����ɂ���āAthis.�i���X�s��ʁj�戵����Row�� <BR>
     * �Ή����鍀�ڒl(*1)�𔻒肵�A �h�戵�\�h�ł����true�A<BR>
     * �ȊOfalse��ԋp����B <BR>
     * <BR>
     * ���@@�����^�C�v��ProductTypeEnum.�����ȊO�̏ꍇ <BR>
     * �@@�F��O��throw����B <BR>
     * class    : WEB3BaseRuntimeException<BR>
     * tag      : SYSTEM_ERROR_80017<BR>
     * <BR>
     * ���@@�����^�C�v��ProductTypeEnum.�����̏ꍇ <BR>
     *   �F(*1)�Ƃ��āA�u�戵�\�v���g�p����<BR>
     * <BR>
     * @@param l_productTypeEnum - �����^�C�v <BR>
     * @@return boolean
     * @@roseuid 4060EE9D0362
     */
    public boolean isHandlingPossible(
        ProductTypeEnum l_productTypeEnum)
    {
        final String STR_METHOD_NAME =
            "isHandingPossible(ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsHandingPossible = false;

        //�����^�C�v��ProductTypeEnum.�����̏ꍇ
        if (l_productTypeEnum.equals(ProductTypeEnum.EQUITY))
        {
            if(WEB3DealtDef.CAN_DEALT.equals(branchMarketDealtCondRow.getMartCanDealtEquity()))
            {
                l_blnIsHandingPossible = true;
            }
            else
            {
                l_blnIsHandingPossible = false;
            }

        }
        //�����^�C�v��ProductTypeEnum.�����ȊO�̏ꍇ
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�C�v = " + l_productTypeEnum);
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsHandingPossible;
    }

    /**
     * (get�������x�P��) <BR>
     * �������x�P�ʂ��擾����B <BR>
     * <BR>
     * �@@�ȉ��̒ʂ蔻�肵�A���x�P�ʒl��ԋp����B <BR>
     * �@@�|�����̏��敪���ꕔ���̏ꍇ�́h�������x�P�ʁi�ꕔ���j�h��ԋp����B 
     * <BR>
     * �@@�|�����̏��敪���񕔏��̏ꍇ�́h�������x�P�ʁi�񕔏��j�h��ԋp����B 
     * <BR>
     * �@@�|�����̏��敪����L�ȊO�̏ꍇ�́h�������x�P�ʁi�ꕔ���j�h��ԋp����B 
     * <BR>
     * <BR>
     * @@param l_strListingDivision - (���敪) <BR>
     * ������������}�X�^�ɂĒ�`����Ă�����敪�B <BR>
     * @@return double
     * @@roseuid 4064038C0389
     */
    public double getDealingLimitUnit(String l_strListingDivision)
    {
        final String STR_METHOD_NAME = "getDealingLimitUnit(String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
        {
            // ���敪���ꕔ���̏ꍇ
            log.debug("���敪���ꕔ���̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return branchMarketDealtCondRow.getLimitedUnit1stSec();
        }
        else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
        {
            // ���敪���񕔏��̏ꍇ
            log.debug("���敪���񕔏��̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return branchMarketDealtCondRow.getLimitedUnit2ndSec();
        }
        else
        {
            // ���敪����L�ȊO�̏ꍇ
            log.debug("���敪����L�ȊO�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return branchMarketDealtCondRow.getLimitedUnit1stSec();
        }
    }

    /**
     * �i(get���X�s��ʁj�戵�����j<BR>
     * �istatic���\�b�h�j <BR>
     * �����̕��X�ɊY������i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾����B <BR>
     * ���s��R�[�h�����Ŏ擾����B <BR>
     * <BR>
     * �P�jDB���� <BR>
     * �@@�i���X�s��ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = ���X.getInstitution().getInstitutionCode() <BR>
     * �@@���X�R�[�h = ���X.getBranchCode() <BR>
     * �@@���s��R�[�h�����Ń\�[�g���Ď擾����B <BR>
     * <BR>
     * �Q�j�I�u�W�F�N�g���� <BR>
     * �@@�������ʂ́i���X�s��ʁj�戵�����s�I�u�W�F�N�g���w�肵�A <BR>
     * �@@�i���X�s��ʁj�戵�����I�u�W�F�N�g�𐶐�����B <BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B <BR>
     * <BR>
     * @@param l_branch - ���X�I�u�W�F�N�g
     * @@return webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond
     * @@throws WEB3SystemLayerException
     * @@roseuid 40640F69033B
     */
    public static WEB3GentradeBranchMarketDealtCond[] getHandlingCondBranchMarket(WEB3GentradeBranch l_branch)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getHandingCondBranchMarket(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);

        //�،���ЃR�[�h 
        String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
        //���X�R�[�h
        String l_strBranchCode = l_branch.getBranchCode();

        List l_lisRecords = null;
        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");   //�،���ЃR�[�h
            l_sbWhere.append(" and  branch_code = ? ");    //���X�R�[�h

            Object[] l_objBranchMarketDealtCondWhere = { 
                l_strInstitutionCode,    //�،���ЃR�[�h
                l_strBranchCode          //���X�R�[�h
                }; 

            //���s��R�[�h�����Ń\�[�g���Ď擾����B
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                BranchMarketDealtCondRow.TYPE,
                l_sbWhere.toString(),
                "to_number(market_code) ",
                null,
                l_objBranchMarketDealtCondWhere
                );
        }
        catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketDealtCond.class.getName()
                + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketDealtCond.class.getName()
                + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
   
        int l_intSize = l_lisRecords.size();
        BranchMarketDealtCondRow l_rowLoop = null;
        WEB3GentradeBranchMarketDealtCond l_gentradeHandingCondBranchMarketLoop = null;
        WEB3GentradeBranchMarketDealtCond[] l_branchMarkets = 
            new WEB3GentradeBranchMarketDealtCond[l_lisRecords.size()];

        for (int l_loop = 0; l_loop < l_intSize; l_loop++)
        {
            l_rowLoop = (BranchMarketDealtCondRow) l_lisRecords.get(l_loop);

            l_gentradeHandingCondBranchMarketLoop =
                new WEB3GentradeBranchMarketDealtCond(l_rowLoop);

            l_branchMarkets[l_loop] = l_gentradeHandingCondBranchMarketLoop;
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_branchMarkets;
    }

    /**
     * (get�s��R�[�h) <BR>
     * �{�I�u�W�F�N�g���ێ�����s��R�[�h���擾����B <BR>
     * <BR>
     * this.�i���X�s��ʁj�戵����Row.�s��R�[�h��ԋp����B <BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4064213502BE
     */
    public String getMarketCode()
    {
        return this.branchMarketDealtCondRow.getMarketCode();
    }

    /**
     * (get�戵�\�s��) <BR>
     * �istatic���\�b�h�j <BR>
     * �����̕��X�ɊY������i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾���A <BR>
     * �����̖����^�C�v�A�M�p����敪�ɊY������I�u�W�F�N�g�̎s��R�[�h�݂̂� <BR>
     * ArrayList�ɐݒ肵�ĕԂ��B <BR>
     * ArrayList�ւ̐ݒ�́A�s��R�[�h�����Ƃ���B <BR>
     * <BR>
     * �P�j�f�[�^�擾 <BR>
     * �@@this.get�i���X�s��ʁj�戵����(���X)�ɂ��A <BR>
     * �@@�����̕��X�ɊY������i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾����B <BR>
     * <BR>
     * �Q�j�戵�\�`�F�b�N <BR>
     * �@@�P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �@@�`�F�b�N���e�F <BR>
     * �@@this.is�戵�\(�����^�C�v)==true�̏ꍇ�A <BR>
     * �@@���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �R�j�쐬����ArrayList��Ԃ��B <BR>
     * @@param l_genBranch - ���X�I�u�W�F�N�g <BR>
     * @@param l_productTypeEnum - �����^�C�v <BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 40A069440336
     */
    public static String[] getHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getHandlingPossibleMarket(WEB3GentradeBranch, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jget�i���X�s��ʁj�戵����(���X)
        WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(l_genBranch);
        
        //�Q�j�戵�\�`�F�b�N
        ArrayList l_lstHandlingPossibleMarkets =  new ArrayList();
        int l_intLength = l_handingCondBranchMarkets.length;
        //this.is�戵�\(�����^�C�v)==true�̏ꍇ�A
        //���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B
        for (int i = 0; i < l_intLength; i++)
        {
            if(l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum))
            {
                String l_strMarketCode = l_handingCondBranchMarkets[i].getMarketCode();
                if(l_lstHandlingPossibleMarkets.contains(l_strMarketCode) == false)
                {
                    l_lstHandlingPossibleMarkets.add(l_strMarketCode);
                }
            }
        }
        
        //�R�j�쐬�����f�[�^��Ԃ��B
        l_intLength = l_lstHandlingPossibleMarkets.size();
        String[] l_strHandlingPossibleMarkets = new String[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            l_strHandlingPossibleMarkets[i] = (String)l_lstHandlingPossibleMarkets.get(i);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;

    }
    
    /**
     * (get�戵�\�s��) <BR>
     * �istatic���\�b�h�j <BR>
     * �����̏،���ЃR�[�h�ɊY������i���X�s��ʁj�戵����<BR>
     * �I�u�W�F�N�g��S�Ď擾���A�����̖����^�C�v�ɊY������I�u�W�F�N�g<BR>
     * �̎s��R�[�h�݂̂�ArrayList�ɐݒ肵�ĕԂ��BArrayList�ւ̐ݒ�<BR>
     * �́A�s��R�[�h�����Ƃ���B<BR>
     *  <BR>
     * �P�j�f�[�^�擾<BR>
     * this.get�i���X�s��ʁj�戵����(.�،���ЃR�[�h)�ɂ��A<BR>
     * �����̏،���ЃR�[�h�ɊY������i���X�s��ʁj�戵����<BR>
     * �I�u�W�F�N�g��S�Ď擾����B<BR>
     *  <BR>
     * �Q�j�戵�\�`�F�b�N<BR>
     * �P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     *  <BR>
     * �`�F�b�N���e�F<BR>
     * this.is�戵�\(�����^�C�v)==true�̏ꍇ�A<BR>
     * ���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B<BR>
     * ���������A���Y���R�[�h�̎s��R�[�h������ArrayList��<BR>
     * ���݂���ꍇ�́A�ǉ����Ȃ��B<BR>
     *  <BR>
     * �R�j�쐬����ArrayList.toArray()�̖߂�l��Ԃ��B<BR>
     *  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h <BR>
     * @@param l_productTypeEnum - �����^�C�v <BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     */
    public static String[] getHandlingPossibleMarket(
        String l_strInstitutionCode,
        ProductTypeEnum l_productTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getHandlingPossibleMarket(String, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�f�[�^�擾
        //get�i���X�s��ʁj�戵����(.�،���ЃR�[�h)�ɂ��A
        //�����̏،���ЃR�[�h�ɊY������i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾����B
        WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(l_strInstitutionCode);
        
        //�Q�j�戵�\�`�F�b�N
        //�P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B
        //�`�F�b�N���e�F
        //this.is�戵�\(�����^�C�v)==true�̏ꍇ�A���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B
        int l_intSize = l_handingCondBranchMarkets.length;
        List l_lstHandlingPossibleMarkets = new ArrayList();
        for(int i = 0; i < l_intSize; i++)
        {
            if(l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum))
            {
                String l_strMarketCode = l_handingCondBranchMarkets[i].getMarketCode();
                if(l_lstHandlingPossibleMarkets.contains(l_strMarketCode) == false)
                {
                    l_lstHandlingPossibleMarkets.add(l_strMarketCode);
                }
            }
        }
        
        //�R�j�쐬�����f�[�^��Ԃ��B
        String[] l_strHandlingPossibleMarkets = new String[l_lstHandlingPossibleMarkets.size()];
        l_lstHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
        
        
    }
    
    /**
     * (get������戵�\�s��) <BR>
     * �istatic���\�b�h�j <BR>
     * �����̕��X�ɊY������i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾���A<BR>
     * �����̖����^�C�v�ɊY������I�u�W�F�N�g�̎s��R�[�h�̂����A������̎s��<BR>
     * �̎s��R�[�h�݂̂�ArrayList�ɐݒ肵�ĕԂ��BArrayList�ւ̐ݒ�́A<BR>
     * �s��R�[�h�����Ƃ���B<BR>
     *  <BR>
     * �P�j�f�[�^�擾<BR>
     *  this.get�i���X�s��ʁj�戵����(���X)�ɂ��A�����̕��X��<BR>
     *  �Y������i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     *  <BR>
     * �Q�j�戵�\�`�F�b�N�E������`�F�b�N<BR>
     *  �P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     *  <BR>
     * �`�F�b�N���e�F<BR>
     * this.is�戵�\(�����^�C�v)==true�A���� ������̎s��(*1)�̏ꍇ�́A<BR>
     * ���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B<BR>
     *  <BR>
     * (*1)������̎s�� <BR>
     * �g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��(���X.�،���ЃR�[�h, ���Y<BR>
     * ���R�[�h�̎s��R�[�h)�ɂ��s��I�u�W�F�N�g���擾�B<BR>
     * �擾�����s��.�����~��"�����~��"�̏ꍇ�́A������̎s��Ɣ��肷��B<BR>
     * ���s��I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A������̎s��ł͂Ȃ��Ɣ��肷��<BR>
     *  <BR>
     * �R�j�쐬����ArrayList��Ԃ��B<BR>
     * <BR>
     * @@param l_genBranch - ���X�I�u�W�F�N�g <BR>
     * @@param l_productTypeEnum - �����^�C�v <BR>
     * @@return String[]<BR>
     * @@throws WEB3SystemLayerException<BR>
     */
    public static String[] getTradingHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getTradingHandlingPossibleMarket(WEB3GentradeBranch, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_genFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //�P�jget�i���X�s��ʁj�戵����(���X)
        WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(l_genBranch);
        
        //1)�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B
        ArrayList l_lstHandlingPossibleMarkets =  new ArrayList();
        int l_intLength = l_handingCondBranchMarkets.length;
        for (int i = 0; i < l_intLength; i++)
        {        
            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��(���X.�،���ЃR�[�h, 
            //���Y���R�[�h�̎s��R�[�h)�ɂ��s��I�u�W�F�N�g���擾�B
            Market l_market = null; 
            try
            {
                l_market =
                    l_genFinObjectManager.getMarket(
                        l_genBranch.getInstitution().getInstitutionCode(),
                        l_handingCondBranchMarkets[i].getMarketCode());
            }
            catch (NotFoundException nfe)
            {
                //���s��I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A������̎s��ł͂Ȃ��Ɣ��肷��
                log.debug("�s��I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A������̎s��ł͂Ȃ��Ɣ��肷��");
            }
            
            //�擾�����s��.�����~��"�����~��"�̏ꍇ�́A������̎s��Ɣ��肷��B
            //�s��I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A������̎s��ł͂Ȃ��Ɣ��肷��
            boolean l_isTradingMarket = false;
            if(l_market != null)
            {
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                if(WEB3SuspensionDef.SUSPENSION.equals(l_marketRow.getSuspension()) == false)
                {
                    l_isTradingMarket = true;
                }
            }
            
            //this.is�戵�\(�����^�C�v)==true�A���� ������̎s��(*1)�̏ꍇ�́A
            //���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B
            if(l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum) && l_isTradingMarket)
            {
                l_lstHandlingPossibleMarkets.add(l_handingCondBranchMarkets[i].getMarketCode());
            }

        }
        
        //�R�j�쐬�����f�[�^��Ԃ��B
        l_intLength = l_lstHandlingPossibleMarkets.size();
        String[] l_strHandlingPossibleMarkets = new String[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            l_strHandlingPossibleMarkets[i] = (String)l_lstHandlingPossibleMarkets.get(i);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }
    
    /**
     * �i(get���X�s���)�戵�����j<BR>
     * �istatic���\�b�h�j <BR>
     * �����̏،���ЃR�[�h�ɊY������<BR>
     * �i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * ���s��R�[�h�����Ŏ擾����B<BR>
     *  <BR>
     * �P�jDB����<BR>
     * �i���X�s��ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * [����]<BR>
     * �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * ���s��R�[�h�����Ń\�[�g���Ď擾����B<BR>
     *  <BR>
     * �Q�j�I�u�W�F�N�g����<BR>
     * �������ʂ́i���X�s��ʁj�戵�����s�I�u�W�F�N�g���w�肵�A<BR>
     * �i���X�s��ʁj�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     * ���������I�u�W�F�N�g��z��ŕԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h) <BR>
     * @@return webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond[]
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeBranchMarketDealtCond[] getHandlingCondBranchMarket(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getHandingCondBranchMarket(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�i���X�s��ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B
        //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        //���s��R�[�h�����Ń\�[�g���Ď擾����B
        List l_lstRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                BranchMarketDealtCondRow.TYPE,
               "institution_code = ?",
                "to_number(market_code) ",
                null,
                new Object[]{l_strInstitutionCode}
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketDealtCond.class.getName()
                    + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j�I�u�W�F�N�g����
        //�������ʂ́i���X�s��ʁj�戵�����s�I�u�W�F�N�g���w�肵�A
        //�i���X�s��ʁj�戵�����I�u�W�F�N�g�𐶐�����B���������I�u�W�F�N�g��z��ŕԋp����B
        int l_intSize = l_lstRecords.size();
        WEB3GentradeBranchMarketDealtCond[] l_gentBranchMarketDealtConds = 
            new WEB3GentradeBranchMarketDealtCond[l_intSize];
        BranchMarketDealtCondRow l_branchMarketDealtCondRow;
        for(int i = 0; i < l_intSize; i++)
        {
            l_branchMarketDealtCondRow = (BranchMarketDealtCondRow)l_lstRecords.get(i);
            l_gentBranchMarketDealtConds[i] =
                new WEB3GentradeBranchMarketDealtCond(l_branchMarketDealtCondRow);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_gentBranchMarketDealtConds;    
    }
    
    /**
     * (is�戵�\�s��) <BR>
     * �istatic���\�b�h�j <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�ɊY������i���X�s��ʁj�戵<BR>
     * �����I�u�W�F�N�g��S�Ď擾���A�擾���R�[�h���ɁA�w��s��́A<BR>
     * �戵�\�v���p�e�B=="�戵�\"�̃��R�[�h���܂܂�Ă���ꍇ��<BR>
     * true���A�܂܂�Ă��Ȃ��ꍇ��false���A���ꂼ��ԋp����B<BR>
     *  <BR>
     * �P�j�@@�f�[�^�擾<BR>
     *  <BR>
     * �P�|�P�j�����̕��X�R�[�h == null�i���X�w��Ȃ��i�S���X�w��j�j�̏ꍇ<BR>
     *  <BR>
     * this.get�i���X�s��ʁj�戵����(�����̏،���ЃR�[�h)�ɂ��A<BR>
     * �����̏،���ЃR�[�h�ɊY������i���X�s��ʁj�戵�����I�u�W�F�N�g<BR>
     * ��S�Ď擾����B<BR>
     *  <BR>
     * �P�|�Q�j�����̕��X�R�[�h != null�i���X�w�肠��j�̏ꍇ<BR>
     *  <BR>
     * this.get�i���X�s��ʁj�戵����(.���X)�ɂ��A������<BR>
     * �،���ЃR�[�h�A���X�R�[�h�ɊY������i���X�s��ʁj�戵����<BR>
     * �I�u�W�F�N�g��S�Ď擾����B<BR>
     *  <BR>
     * �����X�I�u�W�F�N�g�́A�g���A�J�E���g�}�l�[�W��.get���X(<BR>
     *    �����̏،���ЃR�[�h, �����̕��X�R�[�h)�ɂ��擾����B<BR>
     *  <BR>
     * �Q�j�@@�戵�\�`�F�b�N <BR>
     *   �P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     *  <BR>
     * �`�F�b�N���e�F<BR>
     * �s��R�[�h==�����̎s��R�[�h�A���@@<BR>
     * this.is�戵�\(�����^�C�v)==true�̏ꍇ�A<BR>
     * true��ԋp����B<BR>
     *  <BR>
     * �R�j�@@�P�j�ŊY���f�[�^�Ȃ��A�������́@@<BR>
     * �擾�����S�I�u�W�F�N�g���S�ĂQ�j�Ŏ戵�s�Ɣ��f���ꂽ�ꍇ�A<BR>
     * false��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h) <BR>
     * @@param l_productTypeEnum - (�����^�C�v) <BR>
     * @@param l_strBranchCode - (���X�R�[�h) <BR>
     *     ���X�̎w��Ȃ��i�S���X��ΏۂƂ���j�̏ꍇ�́Anull���Z�b�g<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@return boolean<BR>
     * @@throws WEB3SystemLayerException
     */
    public static boolean isHandlingPossibleMarket(
        String l_strInstitutionCode,
        ProductTypeEnum l_productTypeEnum,
        String l_strBranchCode,
        String l_strMarketCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "isHandlingPossibleMarket(String, ProductTypeEnum, String, String)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //�P�|�P�j�����̕��X�R�[�h == null�i���X�w��Ȃ��i�S���X�w��j�j�̏ꍇ
        //this.get�i���X�s��ʁj�戵����(�����̏،���ЃR�[�h)�ɂ��A
        //�����̏،���ЃR�[�h�ɊY������i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾����
        WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets;
        if(l_strBranchCode == null)
        {
            l_handingCondBranchMarkets =
                getHandlingCondBranchMarket(l_strInstitutionCode);     
        }
        else
        {
            //�P�|�Q�j�����̕��X�R�[�h != null�i���X�w�肠��j�̏ꍇ
            //this.get�i���X�s��ʁj�戵����(.���X)�ɂ��A������
            //�،���ЃR�[�h�A���X�R�[�h�ɊY������i���X�s��ʁj�戵����
            //�I�u�W�F�N�g��S�Ď擾����B
            try
            {
                Institution l_institution =
                    l_finApp.getAccountManager().getInstitution(l_strInstitutionCode);
                WEB3GentradeBranch l_genBranch =
                    (WEB3GentradeBranch) l_finApp.getAccountManager().getBranch(l_institution,l_strBranchCode);
                l_handingCondBranchMarkets =
                    getHandlingCondBranchMarket(l_genBranch);     
            }
            catch (NotFoundException nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeBranchMarketDealtCond.class.getName()
                        + "."
                        + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
        }
        
        //�Y���f�[�^�Ȃ��Afalse��ԋp����B
        if(l_handingCondBranchMarkets == null || l_handingCondBranchMarkets.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�戵�\�`�F�b�N 
        //�P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B
        //�`�F�b�N���e�F
        //�s��R�[�h==�����̎s��R�[�h�A���@@this.is�戵�\(�����^�C�v)==true�̏ꍇ�A
        //true��ԋp����B
        int l_intSize = l_handingCondBranchMarkets.length;
        for(int i = 0; i < l_intSize; i++)
        {
            if(l_handingCondBranchMarkets[i].getMarketCode().equals(l_strMarketCode) 
            && l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
       
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    
}
@
