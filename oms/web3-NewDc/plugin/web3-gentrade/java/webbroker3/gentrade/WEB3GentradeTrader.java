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
filename	WEB3GentradeTrader.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����(WEB3GentradeTrader.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �{���@@�瑐(SRA) �V�K�쐬
Revesion History : 2004/11/09 羐� (���u) get���҂�ǉ�
*/

package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TraderImpl;

/**
 * �ڋq�̈��҂�\������B<BR>
 * xTrade�ł́A���O�C���F�؂ɂāA��s���O�C�����o����Ƃ����֘A�ň��҂�\�����Ă���<BR>
 * ���A�Ɩ��I�ȈӖ��ł̈��҂Ƃ��āA���X�E�ڋq�Ɋ֘A�t����B<BR>
 * xTrade��Trade���g�������N���X�B<BR>
 */
public class WEB3GentradeTrader extends TraderImpl
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTrader.class);

    /**
     * �R���X�g���N�^�B<BR>
     *<BR> 
     * @@param l_institution �،���ЃI�u�W�F�N�g
     * @@param l_strTraderCode ���҃R�[�h
     * @@param l_strBranchCode ���X�R�[�h
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     */
    public WEB3GentradeTrader(
        Institution l_institution,
        String l_strTraderCode,
        String l_strBranchCode)
        throws DataQueryException, DataNetworkException
    {
        super(l_institution, l_strTraderCode, l_strBranchCode);
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_lngLoginId - ���O�C��ID
     * @@param l_isLoginId - ���O�C���L��
     * @@throws DataQueryException 
     * @@throws DataNetworkException
     * @@roseuid 400F9DFE039B
     */
    public WEB3GentradeTrader(long l_lngLoginId, boolean l_isLoginId)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngLoginId, l_isLoginId);
    }
    
    /**
     * (get����)<BR>
     * <BR>
     * �istatic���\�b�h�j<BR>
     *  <BR>
     * �P�j�@@��������������̐擪�ɁA�،���Џ�����}������B<BR>
     *  <BR>
     * "�،���ЃR�[�h = <BR>
     * " + �Ǘ��ҁi�I�y���[�^�j.get�،���ЃR�[�h(). �،���ЃR�[�h<BR> 
     *   + ��������������<BR>
     *  <BR>
     * �Q�j�@@���҃e�[�u������<BR>
     * QueryProcessor.doFindAllQuery( )�ɂ��A���ҍs���擾����B<BR> 
     *  <BR>
     *   [doFindAllQuery()�Ɏw�肷�����] <BR>
     *   rowType�F�@@����Row.TYPE <BR>
     *   where�F�@@�P�j�ŕҏW������������������ <BR>
     *   orderBy�F�@@�\�[�g���� <BR>
     *   conditions�F�@@null <BR>
     *   bindVars�F�@@���������f�[�^�R���e�i <BR>
     *  <BR>
     * �R�j�@@�������ʂ��A�����Ɉ�v����v�f�̈���List�i�FArrayList�j��<BR>
     * �ҏW����B����List�i�FArrayList�j�𐶐����A�Q�j�Ŏ擾����<BR>
     * �e�v�f�ɂ��āA�R�|�P�j�`�R�|�R�j�����{����B
     *  <BR>
     * �R�|�P�j�@@�Ώۗv�f�̍s�I�u�W�F�N�g���w�肵�A���҃I�u�W�F�N�g��<BR>
     * ��������B<BR>
     * <BR>
     * �R�|�Q�j�@@���O�C���񐔂̔��� �����O�C���񐔏�����<BR>
     * �w�肳��Ă���ꍇ<BR>
     * �i���O�C���G���[�� != null && ���O�C���G���[�� > 0�j &&<BR>
     * �i���҂̃��O�C���G���[�񐔁�1 �� �����̃��O�C���G���[�񐔁j<BR>
     * �ł���΁A�Ώۗv�f�ɂ��āA�ȍ~�̏����͍s��Ȃ��B<BR>
     *  <BR>
     * ��1 ���҂̃��O�C���G���[�񐔂̎擾 <BR>
     * ����.getLoginId()�ɂĎ擾�������O�C���h�c�Ɉ�v����s��<BR>
     * ���O�C���e�[�u������擾����B�擾�����s�̃��O�C���G���[�񐔁B<BR>
     *  <BR>
     * �R�|�R�j�@@���X�̔��� ���I�y���[�^���S���X���łȂ��ꍇ�A<BR>
     * �����X�̈��ҍs�͎戵���s�B<BR>
     * �i�Ǘ��ҁi�I�y���[�^�j.is�S���X���� == false�j && <BR>
     * �i�R�|�P�j�Ő��������Ǘ���.get���X�R�[�h() != ����.get���X�R�[�h()�j<BR>
     * �ł���΁A�Ώۗv�f�ɂ��āA�ȍ~�̏����͍s��Ȃ��B�icontinue;�j<BR>
     *  <BR>
     * �R�|�S�j�@@����List�i�FArrayList�j�ɑΏۗv�f��ǉ��i�Fadd�j����B<BR>
     *  <BR>
     * �S�j�@@���Ҕz��ԋp<BR>
     * ����List�i�FArrayList�j��z��ɕϊ��itoArray()�j���A�ԋp����B<BR>
     * <BR>
     * @@param l_operator - (�Ǘ��ҁi�I�y���[�^�j) <BR>
     * @@param l_strWhere - (��������������) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@param l_bindVars - (���������f�[�^�R���e�i) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@param l_strOrderBy - (�\�[�g����) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@param l_loginErrorTimes - (���O�C���G���[��) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeTrader[] getTraders(
        WEB3Administrator l_operator,
        String l_strWhere,
        String[] l_bindVars,
        String l_strOrderBy,
        Integer l_loginErrorTimes)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getTraders(WEB3Administrator, String, String[], String, Integer)";
        log.entering(STR_METHOD_NAME);
        
        //�Ǘ��ҁi�I�y���[�^�j�ɏ،���ЃR�[�h���擾 
        AdministratorParams l_administratorParams =
            (AdministratorParams) l_operator.getDataSourceObject();
        String l_strInstitutionCode = l_administratorParams.getInstitutionCode();
            
        //�P�j�@@��������������̐擪�ɁA�،���Џ�����}������
        String l_strTraderWhere;
        if (l_strWhere == null)
        {
            l_strTraderWhere = " institution_code = ? ";
        }
        else
        {
            l_strTraderWhere = " institution_code = ?  " + l_strWhere;
        }
        String[] l_strTraderBindVars;
        if (l_bindVars == null)
        {
            l_strTraderBindVars = new String[] { l_strInstitutionCode };
        }
        else
        {
            int l_intLength = l_bindVars.length;
            l_strTraderBindVars = new String[l_intLength + 1];
            l_strTraderBindVars[0] = l_strInstitutionCode;
            for (int i = 0; i < l_intLength; i++)
            {
                l_strTraderBindVars[i + 1] = l_bindVars[i];
            }
        }
        
        List l_lstTraders = new ArrayList();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        try
        {
            // �Q�j�@@���҃e�[�u������
            //[doFindAllQuery()�Ɏw�肷�����] 
            //rowType�F�@@����Row.TYPE 
            //where�F�@@�P�j�ŕҏW������������������ 
            //orderBy�F�@@�\�[�g���� 
            //conditions�F�@@null 
            //bindVars�F�@@���������f�[�^�R���e�i
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lstRecords =
                l_queryProcessor.doFindAllQuery(
                    TraderRow.TYPE,
                    l_strTraderWhere,
                    l_strOrderBy,
                    null,
                    l_strTraderBindVars);
            
            //�R�j�@@�������ʂ��A�����Ɉ�v����v�f�̈���List�i�FArrayList�j��ҏW����
            int l_intSize = l_lstRecords.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //�R�|�P�j�@@�Ώۗv�f�̍s�I�u�W�F�N�g���w�肵�A���҃I�u�W�F�N�g�𐶐�����
                TraderRow l_tmpRow =
                    (TraderRow)l_lstRecords.get(i);
                
                
                // ���҂̃��O�C���G���[�񐔂̎擾
                LoginRow l_loginRow = 
                    LoginDao.findRowByPk(l_tmpRow.getLoginId());
                
                //�R�|�Q�j�@@���O�C���񐔂̔���
                if ((l_loginErrorTimes != null)
                    && (l_loginErrorTimes.intValue() > 0)
                    && (l_loginRow.getFailureCount() < l_loginErrorTimes.intValue()))
                {
                    continue;
                }
                
                //�R�|�R�j�@@���X�̔���
                if ((l_operator.isAllBranchsPermission() == false)
                    && (WEB3Toolkit.isEquals(l_operator.getBranchCode(),l_tmpRow.getBranchCode()) == false))
                {
                    continue;
                }
                
                //�R�|�S�j����List�i�FArrayList�j�ɑΏۗv�f��ǉ��i�Fadd�j����B
                Institution l_institution = 
                    l_finApp.getAccountManager().getInstitution(l_tmpRow.getInstitutionCode());
                WEB3GentradeTrader l_tmpGentradeTrader =
                    new WEB3GentradeTrader(
                        l_institution,
                        l_tmpRow.getTraderCode(),
                        l_tmpRow.getBranchCode());
                l_lstTraders.add(l_tmpGentradeTrader);
                
            }
            
            //�S�j�@@���Ҕz��ԋp
            l_intSize = l_lstTraders.size();
            WEB3GentradeTrader[] l_genTraders = new WEB3GentradeTrader[l_intSize];
            for(int i = 0; i < l_intSize; i ++)
            {
                l_genTraders[i] = (WEB3GentradeTrader)l_lstTraders.get(i);
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_genTraders;
            
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME, nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTrader.class.getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTrader.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

    }
}
@
