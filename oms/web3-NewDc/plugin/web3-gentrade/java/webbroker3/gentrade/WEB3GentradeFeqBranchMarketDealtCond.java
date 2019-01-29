head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeFeqBranchMarketDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (���X�s���.�O��)�戵����(WEB3GentradeFeqBranchMarketDealtCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/07 �Г� (���u) �V�K�쐬
Revesion History : 2005/07/19 �Г� (���u) �d�l�ύXNo.146��Ή�
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
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
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondDao;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondRow;
import webbroker3.util.WEB3LogUtility;

/**
 *�i���X�s���.�O���j�戵����<BR>
 * �،���ЁA���X�A�s�ꖈ�̎戵�\����������\������B<BR>
 * �O�������Ŏg�p�B<BR>
 * <BR>
 *�iDB���C�A�E�g �u�i���X�s���.�O���j�戵�����e�[�u��.xls�v�Q�Ɓj<BR>
 * <BR>
 */
public class WEB3GentradeFeqBranchMarketDealtCond implements BusinessObject
{

    /**
     * �i���X�s���.�O���j�戵����Row�s�I�u�W�F�N�g <BR>
     * �iDAO���������N���X�j<BR>
     */
    private FeqBranchMarketDealtCondRow feqBranchMarketDealtCondRow;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeFeqBranchMarketDealtCond.class);

    /**
     * �R���X�g���N�^�B <BR>
     * (���X�s���)�戵���� <BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A <BR>
     * �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_feqBranchMarketDealtCondRow �i���X�s���.�O���j�戵�����s�I�u�W�F�N�g
     * @@roseuid 40640F0102AE
     */
    public WEB3GentradeFeqBranchMarketDealtCond(
        FeqBranchMarketDealtCondRow l_feqBranchMarketDealtCondRow)
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeFeqBranchMarketDealtCond(FeqBranchMarketDealtCondRow)";
        if(l_feqBranchMarketDealtCondRow == null)
        {
            log.error("�i���X�s���.�O���j�戵�����s�I�u�W�F�N�g = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�i���X�s���.�O���j�戵�����s�I�u�W�F�N�g = null");
        }

        this.feqBranchMarketDealtCondRow = l_feqBranchMarketDealtCondRow;
    }

    /**
     * �R���X�g���N�^�B <BR>
     * �����̏����Ɉ�v����i���X�s���.�O���j�戵�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �����̒l�ɂāi���X�s���.�O���j�戵�����e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR> 
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�i���X�s���.�O���j�戵����Row�j��<BR>
     * this.�i���X�s���.�O���j�戵�����ɃZ�b�g����B<BR> 
     * <BR>
     * @@param l_strInstitutionCode �،���ЃR�[�h <BR>
     * @@param l_strBranchCode ���X�R�[�h <BR>
     * @@param l_strMarketCode �s��R�[�h <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 4060ED5C0373
     */
    public WEB3GentradeFeqBranchMarketDealtCond(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeFeqBranchMarketDealtCond(String, String, String)";
        log.entering(STR_METHOD_NAME);

        FeqBranchMarketDealtCondRow l_row = null;
        try
        {
            //�i���X�s���.�O���j�戵�����e�[�u��
            l_row = FeqBranchMarketDealtCondDao.findRowByPk(
                 l_strInstitutionCode,
                 l_strBranchCode,
                 l_strMarketCode
                 );
        }
        catch (DataFindException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
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
            this.feqBranchMarketDealtCondRow = l_row;
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
     * this.���X�s���.�O��-�戵����Row��ԋp����B
     * @@return Object
     * @@roseuid 4060ED5C0372
     */
    public Object getDataSourceObject()
    {
        return this.feqBranchMarketDealtCondRow;
    }

    /**
     * (get�i���X�s���.�O���j�戵����)<BR>
     * �istatic���\�b�h�j<BR>
     * �����̕��X�ɊY������i���X�s���.�O���j�戵�����I�u�W�F�N�g��<BR>
     * �S�Ď擾����B<BR> 
     * ���s��R�[�h�����Ŏ擾����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �i���X�s���.�O���j�戵�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ���X.getInstitution().getInstitutionCode()<BR>
     * �@@���X�R�[�h = ���X.getBranchCode()<BR>
     * �@@���s��R�[�h�����Ń\�[�g���Ď擾����B<BR>
     * <BR>
     * �Q�j�I�u�W�F�N�g����<BR>
     * �@@�������ʂ́i���X�s���.�O���j�戵�����s�I�u�W�F�N�g���w�肵�A<BR>
     * �@@�i���X�s���.�O���j�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B<BR> 
     * <BR>
     * @@param l_branch ���X�I�u�W�F�N�g
     * @@return webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 40640F69033B
     */
    public static WEB3GentradeFeqBranchMarketDealtCond[] 
        getFeqHandlingCondBranchMarket(
            WEB3GentradeBranch l_branch)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getFeqHandlingCondBranchMarket(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);

        //�،���ЃR�[�h 
        String l_strInstitutionCode = 
            l_branch.getInstitution().getInstitutionCode();
        //���X�R�[�h
        String l_strBranchCode = l_branch.getBranchCode();

        List l_lisRecords = null;
        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");   //�،���ЃR�[�h
            l_sbWhere.append(" and  branch_code = ? ");   //���X�R�[�h

            Object[] l_objBranchMarketDealtCondWhere = { 
                l_strInstitutionCode,    //�،���ЃR�[�h
                l_strBranchCode          //���X�R�[�h
                }; 

            //���s��R�[�h�����Ń\�[�g���Ď擾����B
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                FeqBranchMarketDealtCondRow.TYPE,
                l_sbWhere.toString(),
                " market_code asc ",
                null,
                l_objBranchMarketDealtCondWhere
                );
        }
        catch (DataException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeFeqBranchMarketDealtCond.class.getName()
                + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        //�Q�j�I�u�W�F�N�g����
        int l_intSize = l_lisRecords.size();
        FeqBranchMarketDealtCondRow l_rowLoop = null;
        WEB3GentradeFeqBranchMarketDealtCond 
            l_gentradeFeqHandingCondBranchMarketLoop = null;
        WEB3GentradeFeqBranchMarketDealtCond[] l_feqBranchMarkets = 
            new WEB3GentradeFeqBranchMarketDealtCond[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_rowLoop = (FeqBranchMarketDealtCondRow) l_lisRecords.get(i);

            l_gentradeFeqHandingCondBranchMarketLoop =
                new WEB3GentradeFeqBranchMarketDealtCond(l_rowLoop);

            l_feqBranchMarkets[i] = l_gentradeFeqHandingCondBranchMarketLoop;
        }
                
        log.exiting(STR_METHOD_NAME);
        return l_feqBranchMarkets;
    }

    /**
     * (get�i���X�s���.�O���j�戵����)<BR>
     * �istatic���\�b�h�j<BR>
     * �����̏،���ЃR�[�h�ɊY������<BR>
     * �i���X�s���.�O���j�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * ���s��R�[�h�����Ŏ擾����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@�i���X�s���.�O���j�戵�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@���s��R�[�h�����Ń\�[�g���Ď擾����B<BR>
     * <BR>
     * �Q�j�I�u�W�F�N�g����<BR>
     * �@@�������ʂ́i���X�s���.�O���j�戵�����s�I�u�W�F�N�g���w�肵�A<BR>
     * �@@�i���X�s���.�O���j�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B<BR> 
     * <BR>
     * @@param l_strInstitutionCode (�،���ЃR�[�h) <BR>
     * @@return webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond[]
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeFeqBranchMarketDealtCond[] 
        getFeqHandlingCondBranchMarket(
            String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeFeqBranchMarketDealtCond(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�i���X�s��ʁj�戵�����e�[�u�����ȉ��̏����Ō�������B
        //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        //���s��R�[�h�����Ń\�[�g���Ď擾����B
        List l_lstRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                FeqBranchMarketDealtCondRow.TYPE,
                " institution_code = ?",
                " market_code asc ",
                null,
                new Object[]{l_strInstitutionCode}
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeFeqBranchMarketDealtCond.class.getName()
                    + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //�Q�j�I�u�W�F�N�g����
        //�������ʂ́i���X�s��ʁj�戵�����s�I�u�W�F�N�g���w�肵�A
        //�i���X�s��ʁj�戵�����I�u�W�F�N�g�𐶐�����B���������I�u�W�F�N�g��z��ŕԋp����B
        int l_intSize = l_lstRecords.size();
        FeqBranchMarketDealtCondRow l_rowLoop = null;
        WEB3GentradeFeqBranchMarketDealtCond 
            l_gentradeFeqHandingCondBranchMarketLoop = null;
        WEB3GentradeFeqBranchMarketDealtCond[] l_feqBranchMarkets = 
            new WEB3GentradeFeqBranchMarketDealtCond[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_rowLoop = (FeqBranchMarketDealtCondRow) l_lstRecords.get(i);

            l_gentradeFeqHandingCondBranchMarketLoop =
                new WEB3GentradeFeqBranchMarketDealtCond(l_rowLoop);

            l_feqBranchMarkets[i] = l_gentradeFeqHandingCondBranchMarketLoop;
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqBranchMarkets;    
    }

    /**
     * (get�s��R�[�h) <BR>
     * �{�I�u�W�F�N�g���ێ�����s��R�[�h���擾����B<BR>
     * <BR>
     * this.�i���X�s���.�O���j�戵����Row.�s��R�[�h��ԋp����B<BR> 
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4064213502BE
     */
    public String getMarketCode()
    {
        return this.feqBranchMarketDealtCondRow.getMarketCode();
    }

    /**
     * (get�戵�\�s��) <BR>
     * �istatic���\�b�h�j <BR>
     * �����̕��X�ɊY������i���X�s���.�O���j�戵�����I�u�W�F�N�g��S�Ď擾���A<BR>
     * �����̖����^�C�v�ɊY������I�u�W�F�N�g�̎s��R�[�h�݂̂�<BR>
     * ArrayList�ɐݒ肵�ĕԂ��B<BR>
     * ArrayList�ւ̐ݒ�́A�s��R�[�h�����Ƃ���B<BR>
     * <BR>
     * �P�j�f�[�^�擾<BR>
     * �@@this.get�i���X�s���.�O���j�戵����(���X)�ɂ��A<BR>
     * �@@�����̕��X�ɊY������i���X�s���.�O���j�戵�����I�u�W�F�N�g��<BR>
     * �S�Ď擾����B<BR> 
     * �Q�j�戵�\�`�F�b�N<BR>
     * �@@�P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�`�F�b�N���e�F<BR>
     * �@@this.is�戵�\(�����^�C�v)==true�̏ꍇ�A<BR>
     * �@@���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�쐬����ArrayList��Ԃ��B<BR>
     * <BR>
     * @@param l_genBranch ���X�I�u�W�F�N�g
     * @@param l_productTypeEnum �����^�C�v
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

        //�P�jget�i���X�s���.�O���j�戵����(���X)
        WEB3GentradeFeqBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getFeqHandlingCondBranchMarket(l_genBranch);

        //�Q�j�戵�\�`�F�b�N
        ArrayList l_lstHandlingPossibleMarkets =  new ArrayList();
        int l_intLength = l_handingCondBranchMarkets.length;
        //this.is�戵�\(�����^�C�v)==true�̏ꍇ�A
        //���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B
        for (int i = 0; i < l_intLength; i++)
        {
            if(l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum))
            {
                String l_strMarketCode = 
                    l_handingCondBranchMarkets[i].getMarketCode();
                if(!l_lstHandlingPossibleMarkets.contains(l_strMarketCode))
                {
                    l_lstHandlingPossibleMarkets.add(l_strMarketCode);
                }
            }
        }

        //�R�j�쐬�����f�[�^��Ԃ��B
        String[] l_strHandlingPossibleMarkets = 
            new String[l_lstHandlingPossibleMarkets.size()];
        l_lstHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (get�戵�\�s��) <BR>
     * �istatic���\�b�h�j <BR>
     * �����̏،���ЃR�[�h�ɊY������ <BR>
     * �i���X�s���.�O���j�戵�����I�u�W�F�N�g��S�Ď擾���A<BR>
     * �����̖����^�C�v�ɊY������I�u�W�F�N�g�̎s��R�[�h�݂̂�<BR>
     * ArrayList�ɐݒ肵�ĕԂ��B<BR>
     * ArrayList�ւ̐ݒ�́A�s��R�[�h�����Ƃ���B<BR>
     * <BR>
     * �P�j�f�[�^�擾<BR>
     * �@@this.get�i���X�s���.�O���j�戵����(.�،���ЃR�[�h)�ɂ��A<BR>
     * �@@�����̏،���ЃR�[�h�ɊY������<BR>
     * �@@�i���X�s���.�O���j�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �Q�j�戵�\�`�F�b�N<BR>
     * �@@�P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�`�F�b�N���e�F<BR>
     * �@@this.is�戵�\(�����^�C�v)==true�̏ꍇ�A<BR>
     * �@@���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B <BR>
     * �@@���������A���Y���R�[�h�̎s��R�[�h������ArrayList�ɑ��݂���ꍇ�́A<BR>
     * �@@�@@�ǉ����Ȃ��B<BR>
     * <BR>
     * �R�j�쐬����ArrayList.toArray()�̖߂�l��Ԃ��B<BR>
     * <BR> 
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_productTypeEnum �����^�C�v
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
        //get�i���X�s���.�O���j�戵����(.�،���ЃR�[�h)�ɂ��A
        //�����̏،���ЃR�[�h�ɊY������i���X�s��ʁj�戵�����I�u�W�F�N�g��S�Ď擾����B
        WEB3GentradeFeqBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getFeqHandlingCondBranchMarket(l_strInstitutionCode);
        
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
                if(!l_lstHandlingPossibleMarkets.contains(l_strMarketCode))
                {
                    l_lstHandlingPossibleMarkets.add(l_strMarketCode);
                }
            }
        }
        
        //�R�j�쐬�����f�[�^��Ԃ��B
        String[] l_strHandlingPossibleMarkets = 
            new String[l_lstHandlingPossibleMarkets.size()];
        l_lstHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (get������戵�\�s��) <BR>
     * �istatic���\�b�h�j <BR>
     * �����̕��X�ɊY������i���X�s���.�O���j�戵�����I�u�W�F�N�g��<BR>
     * �S�Ď擾���A<BR> 
     * �����̖����^�C�v�ɊY������I�u�W�F�N�g�̎s��R�[�h�̂����A<BR>
     * ������̎s��̎s��R�[�h�݂̂�<BR>
     * ArrayList�ɐݒ肵�ĕԂ��B<BR>
     * ArrayList�ւ̐ݒ�́A�s��R�[�h�����Ƃ���B<BR>
     * <BR>
     * �P�j�f�[�^�擾<BR>
     * �@@this.get�i���X�s���.�O���j�戵����(���X)�ɂ��A<BR>
     * �@@�����̕��X�ɊY������i���X�s���.�O���j�戵�����I�u�W�F�N�g��<BR>
     *   �S�Ď擾����B<BR>
     * <BR>
     * �Q�j�戵�\�`�F�b�N�E������`�F�b�N<BR>
     * �@@�P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�`�F�b�N���e�F<BR>
     * �@@this.is�戵�\(�����^�C�v)==true�A���� ������̎s��(*1)�̏ꍇ�́A<BR>
     * �@@���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@(*1)������̎s��<BR>
     * �@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��(<BR>
     *     ���X.�،���ЃR�[�h, ���Y���R�[�h�̎s��R�[�h)�ɂ��<BR>
     * �@@�@@�s��I�u�W�F�N�g���擾�B<BR>
     * �@@�@@�擾�����s��.�����~��"�����~��"�̏ꍇ�́A<BR>
     *     ������̎s��Ɣ��肷��B<BR>
     * �@@�@@���s��I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A<BR>
     *     ������̎s��ł͂Ȃ��Ɣ��肷��B<BR> 
     * <BR>
     * �R�j�쐬����ArrayList��Ԃ��B<BR> 
     * <BR>
     * @@param l_genBranch ���X�I�u�W�F�N�g
     * @@param l_productTypeEnum �����^�C�v
     * @@return String[]
     * @@throws WEB3SystemLayerException
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
        
        //�P�jget�i���X�s���.�O���j�戵����(���X)
        WEB3GentradeFeqBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getFeqHandlingCondBranchMarket(l_genBranch);
        
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
                if(!WEB3SuspensionDef.SUSPENSION.equals(l_marketRow.getSuspension()))
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
        String[] l_strHandlingPossibleMarkets = 
            new String[l_lstHandlingPossibleMarkets.size()];
        l_lstHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (is�戵�\) <BR>
     * �w�菤�i���戵�\�ł��邩��ԋp����B<BR>
     * <BR>
     * �����̏����ɂ���āAthis.�i���X�s���.�O���j�戵����Row��<BR>
     * �Ή����鍀�ڒl(*1)�𔻒肵�A<BR>
     * �h�戵�\�h�ł����true�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * ���@@�����^�C�v��ProductTypeEnum.�O�������ȊO�̏ꍇ<BR>
     * �@@��O��throw����B<BR>
     * ���@@�����^�C�v��ProductTypeEnum.�O�������̏ꍇ<BR>
     * �@@�@@�F(*1)�Ƃ��āA�u�戵�\�v���g�p����B <BR>
     * <BR>
     * @@param l_productTypeEnum �����^�C�v
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

        //�����^�C�v��ProductTypeEnum.�O�������̏ꍇ
        if (l_productTypeEnum.equals(ProductTypeEnum.FOREIGN_EQUITY))
        {
            if(WEB3DealtDef.CAN_DEALT.equals(
                feqBranchMarketDealtCondRow.getMartCanDealtEquity()))
            {
                l_blnIsHandingPossible = true;
            }
            else
            {
                l_blnIsHandingPossible = false;
            }
        }
        //�����^�C�v��ProductTypeEnum.�O�������ȊO�̏ꍇ
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
     * (is�戵�\�s��) <BR>
     *  (static���\�b�h�j<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�ɊY������<BR>
     * �i���X�s���.�O���j�戵�����I�u�W�F�N�g��S�Ď擾���A<BR>
     * �擾���R�[�h���ɁA�w��s��́A�戵�\�v���p�e�B=="�戵�\"��<BR>
     * ���R�[�h���܂܂�Ă���ꍇ��true���A<BR>
     * �܂܂�Ă��Ȃ��ꍇ��false���A���ꂼ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�f�[�^�擾<BR>
     * <BR>
     * �P�|�P�j�@@�����̕��X�R�[�h == null�i���X�w��Ȃ��i�S���X�w��j�j�̏ꍇ<BR>
     * <BR>
     * this.get�i���X�s���.�O���j�戵����(�����̏،���ЃR�[�h)�ɂ��A<BR>
     * �����̏،���ЃR�[�h�ɊY������<BR>
     * �i���X�s���.�O���j�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �P�|�Q�j�@@�����̕��X�R�[�h != null�i���X�w�肠��j�̏ꍇ<BR>
     * <BR>
     * this.get�i���X�s���.�O���j�戵����(.���X)�ɂ��A<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�ɊY������<BR>
     * �i���X�s���.�O���j�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �����X�I�u�W�F�N�g�́A<BR>
     * �g���A�J�E���g�}�l�[�W��.get���X(�����̏،���ЃR�[�h, �����̕��X�R�[�h)<BR>
     * ���ɂ��擾����B<BR> 
     * <BR>
     * �Q�j�@@�戵�\�`�F�b�N<BR>
     * �P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �`�F�b�N���e�F<BR>
     * �s��R�[�h==�����̎s��R�[�h�A<BR>
     * ���@@this.is�戵�\(�����^�C�v)==true�̏ꍇ�A<BR>
     * true��ԋp����B<BR>
     * <BR>
     * �R�j�@@�P�j�ŊY���f�[�^�Ȃ��A<BR>
     * �@@�@@�@@�������́@@�擾�����S�I�u�W�F�N�g���S�ĂQ�j��<BR>
     * �@@�@@�@@�戵�s�Ɣ��f���ꂽ�ꍇ�A<BR>
     * �@@�@@�@@false��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_productTypeEnum �����^�C�v
     * @@param l_strBranchCode ���X�R�[�h<BR>
     *     ���X�̎w��Ȃ��i�S���X��ΏۂƂ���j�̏ꍇ�́Anull���Z�b�g
     * @@param l_strMarketCode �s��R�[�h
     * @@return boolean
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
        WEB3GentradeFeqBranchMarketDealtCond[] l_handingCondBranchMarkets;
        if(l_strBranchCode == null)
        {
            l_handingCondBranchMarkets =
                getFeqHandlingCondBranchMarket(l_strInstitutionCode);
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
                    getFeqHandlingCondBranchMarket(l_genBranch);     
            }
            catch (NotFoundException nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeFeqBranchMarketDealtCond.class.getName()
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
       
        // �R�j�@@�P�j�ŊY���f�[�^�Ȃ��A
        // �@@�@@�@@�������́@@�擾�����S�I�u�W�F�N�g���S�ĂQ�j��
        // �@@�@@�@@�戵�s�Ɣ��f���ꂽ�ꍇ�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
