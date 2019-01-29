head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����(WEB3EquityContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/01 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2007/05/21 �����F(���u)�@@���f�� 1162
*/

package webbroker3.equity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����j�B<BR>
 * <BR>
 * ���x�[�X�̌�����\������B<BR>
 * �ԍρ^�������n�������̎c����O���X�ŕێ�����B<BR>
 * xTrade��EqTypeContractImpl���g�������N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3EquityContract extends EqTypeContractImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityContract.class);

    /**
     * (����)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����h�c�Ɉ�v����s�������e�[�u�����擾����B<BR>
     * �擾���������s�I�u�W�F�N�g�iEqTypeContractParams�j���w�肵
     * �X�[�p�[�N���X�̃R���X�g���N�^���R�[������B<BR>
     * <BR>
     * @@param l_lngContractId - ����ID<BR>
     * @@return WEB3EquityContract
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 406A6B6100B3
     */
    public WEB3EquityContract(long l_lngContractId) throws DataQueryException, DataNetworkException
    {
        super(l_lngContractId);
    }
    
    /**
     * (����)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̌���Row.����ID�A�y�ш����̌���Row��<BR>
     * �v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_row - ����Row<BR>
     * @@return WEB3EquityContract
     */
    public WEB3EquityContract(EqtypeContractRow l_row)
    {
        super(l_row);
    }

    /**
     * (get���b�N������)<BR>
     * �igetLockedQuantity�̃I�[�o�[���[�h�j<BR>
     * �w��̒����P�ʂ̃��b�N���̐��ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�����ԍώw����Ǎ�<BR>
     * <BR>
     * �ȉ��̏����Ō����ԍώw����e�[�u����ǂށB<BR>
     * <BR>
     * [����]<BR>
     * �����ԍώw����.�����h�c = this.getContractId()<BR>
     * �����ԍώw����.�����P�ʂh�c = ����.�����P�ʂh�c<BR>
     * <BR>
     * �Y���s�����݂��Ȃ��ꍇ��0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���b�N�����ʕԋp<BR>
     * <BR>
     * �擾���������ԍώw����s���ƂɁA
     * �����ԍώw����s.�ԍϒ������� �| �����ԍώw����s.�ԍϖ�萔�� ���v�Z����B<BR>
     * <BR>
     * ��L�v�Z���ʂ́A�S�Ă̕ԍώw����s��SUM�l��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_lngOrderUnitId - �����P�ʂh�c<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 407B88200118
     */
    public double getLockedQuantity(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLockedQuantity";
        log.entering(STR_METHOD_NAME);

        double l_dblReturnValue = 0;
        try
        {
            // �P�j�����ԍώw����Ǎ�

            //���ʂh�c = this.getContractId()
            String l_strWhere = "contract_id = ? and order_unit_id = ?";
            long l_lngContractIdValue = this.getContractId();

            // [����1]:���ʕԍώw����.�����P�ʂh�c = ����.�����P�ʂh�c

            Object[] l_objWhereValues = { new Long(l_lngContractIdValue), new Long(l_lngOrderUnitId)};

            List l_lstReturnList = new ArrayList();

            // �f�[�^���m         
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_lstReturnList = processor.doFindAllQuery(EqtypeClosingContractSpecParams.TYPE, l_strWhere, l_objWhereValues);

            // �Q�j���b�N�����ʕԋp
            int l_intreturnListLength = l_lstReturnList.size();
            if (l_intreturnListLength == 0)
            {
                return 0;
            }
            for (int i = 0; i < l_intreturnListLength; i++)
            {
                // �擾���������ԍώw����s���ƂɁA
                //�����ԍώw����s.�ԍϒ������� �| �����ԍώw����s.�ԍϖ�萔�� ���v�Z����B
                EqtypeClosingContractSpecRow l_contractSpecRow = (EqtypeClosingContractSpecRow) l_lstReturnList.get(i);
                double l_dblExecutedQuantity = l_contractSpecRow.getExecutedQuantity();
                if (Double.isNaN(l_dblExecutedQuantity))
                {
                    l_dblExecutedQuantity = 0;
                }
                double l_dblQuantity = l_contractSpecRow.getQuantity();
                if (Double.isNaN(l_dblQuantity))
                {
                    l_dblQuantity = 0;
                }

                l_dblReturnValue += (l_dblQuantity - l_dblExecutedQuantity);
            }

        }
        catch (DataQueryException l_ex)
        {
            log.entering(STR_METHOD_NAME);
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.entering(STR_METHOD_NAME);
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);

        //��L�v�Z���ʂ́A�S�Ă̕ԍώw����s��SUM�l��ԋp����B
        return l_dblReturnValue;
    }

    /**
     * (get�ԍϖ��ϐ���)<BR>
     * �w��̒����P�ʂɊ֘A����ԍϖ��ϐ��ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�ԍώw����Ǎ�<BR>
     * <BR>
     * �ȉ��̏����Ō����ԍώw����e�[�u����ǂށB<BR>
     * <BR>
     * [����]<BR>
     * �����ԍώw����.�����h�c = this.getContractId()<BR>
     * �����ԍώw����.�����P�ʂh�c = ����.�����P�ʂh�c<BR>
     * <BR>
     * �Y���s�����݂��Ȃ��ꍇ��0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ԍϖ��ϐ��ʕԋp<BR>
     * <BR>
     * �擾���������ԍώw����s.�ԍϖ�萔�ʂ̍��v�l��ԋp����B<BR>
     * <BR>
     * @@param l_lngOrderUnitId - �����P�ʂh�c<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 407B8A3503B8
     */
    public double getClosingExecutedQuantity(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getClosingExecutedQuantity";
        log.entering(STR_METHOD_NAME);

        double l_dblReturnValue = 0;
        try
        {
            // �P�j�����ԍώw����Ǎ�

            //���ʂh�c = this.getContractId()
            String l_strWhere = " contract_id = ? and order_unit_id = ? ";
            long l_lngContractIdValue = this.getContractId();

            // [����1]:���ʕԍώw����.�����P�ʂh�c = ����.�����P�ʂh�c

            Object[] l_objWhereValues = { new Long(l_lngContractIdValue), new Long(l_lngOrderUnitId)};

            List l_lstReturnList = new ArrayList();

            // �f�[�^���m         
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_lstReturnList = processor.doFindAllQuery(EqtypeClosingContractSpecParams.TYPE, l_strWhere, l_objWhereValues);

            // �Q�j���b�N�����ʕԋp
            int l_intreturnListLength = l_lstReturnList.size();
            if (l_intreturnListLength == 0)
            {
                return 0;
            }
            for (int i = 0; i < l_intreturnListLength; i++)
            {
                // �擾���������ԍώw����s���ƂɁA
                //�擾���������ԍώw����s���ƂɁA�����ԍώw����s.�ԍϒ������� �| �����ԍώw����s.�ԍϖ�萔�� ���v�Z����B
                EqtypeClosingContractSpecRow l_contractSpecRow = (EqtypeClosingContractSpecRow) l_lstReturnList.get(i);
                double l_dblexecutedQuantity = l_contractSpecRow.getExecutedQuantity();
                if (Double.isNaN(l_dblexecutedQuantity))
                {
                    l_dblexecutedQuantity = 0;
                }

                l_dblReturnValue += l_dblexecutedQuantity;
            }

        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);

        //�擾���������ԍώw����s.�ԍϖ�萔�ʂ̍��v�l��ԋp����B
        return l_dblReturnValue;
    }

    /**
     * (get�]�����v)<BR>
     * �w��P���ŁA�w�萔�ʂ�ԍς����ꍇ�̕]�����v���擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * ��this.isLong( )==true�i�����j�̏ꍇ<BR>
     * �i�p�����[�^.�v�Z�P�� �~ �p�����[�^.���ʁj �| this.get�����(�p�����[�^.����)<BR>
     * <BR>
     * ��this.isLong( )==false�i�����j�̏ꍇ<BR>
     * this.get�����(�p�����[�^.����) �| �i�p�����[�^.�v�Z�P�� �~ �p�����[�^.���ʁj<BR>
     * <BR>
     * @@param l_dblCalcUnitPrice - �v�Z�P���B<BR>
     * @@param l_dblQuantity - ����<BR>
     * @@return double
     * @@roseuid 40601CC8000F
     */
    public double getAppraisalProfitOrLoss(double l_dblCalcUnitPrice, double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "getAppraisalProfitOrLoss";
        log.entering(STR_METHOD_NAME);

        double l_dblReturnValue = 0;
        //this.isLong( )==true�i�����j�̏ꍇ
        if (this.isLong())
        {
            l_dblReturnValue = (l_dblCalcUnitPrice * l_dblQuantity) - this.getContractAmount(l_dblQuantity);
        }
        //this.isLong( )==false�i�����j�̏ꍇ
        else
        {
            l_dblReturnValue = this.getContractAmount(l_dblQuantity) - (l_dblCalcUnitPrice * l_dblQuantity);
        }

        log.exiting(STR_METHOD_NAME);
        return new BigDecimal(l_dblReturnValue).longValue();
    }

    /**
     * (get�����)<BR>
     * �w�萔�ʂɂ����錚������擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �������������ɂ��A���P���ɂ͏����_�ȉ���Q�ʂ܂Œl�������Ă��邱�Ƃ�����̂ŁA<BR>
     * ���v�Z���ʂ̉~�����؎̂��s���B<BR>
     * <BR>
     * getContractPrice() �~ �p�����[�^.����<BR>
     * @@param l_dblQuantity - ����<BR>
     * @@return double
     * @@roseuid 4060201A0239
     */
    public double getContractAmount(double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "getContractAmount";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return new BigDecimal(this.getContractPrice() * l_dblQuantity).longValue();
    }

    /**
     * �iget���萔���j<BR>
     * <BR>
     * �w�萔�ʕ��̌��萔�����v�Z����B<BR>
     * <BR>
     * this.get���萔��(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̌��萔�����擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getSetupFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSetupFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getSetupFee(l_dblQuantity, 0L);
    }

    /**
     * �iget���萔������Łj<BR>
     * <BR>
     * �w�萔�ʕ��̌��萔������ł��v�Z����B<BR>
     * <BR>
     * this.get���萔�������(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̌��萔������ł��擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getSetupFeeTax(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSetupFeeTax(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getSetupFeeTax(l_dblQuantity, 0L);
    }

    /**
     * �iget���`�������j<BR>
     * <BR>
     * �w�萔�ʕ��̖��`���������v�Z����B<BR>
     * <BR>
     * this.get���`������(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̖��`���������擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getNameTransferFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNameTransferFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getNameTransferFee(l_dblQuantity, 0L);
    }

    /**
     * �iget���`����������Łj<BR>
     * <BR>
     * �w�萔�ʕ��̖��`����������ł��v�Z����B<BR>
     * <BR>
     * this.get���`�����������(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̖��`����������ł��擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getNameTransferFeeTax(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNameTransferFeeTax(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getNameTransferFeeTax(l_dblQuantity, 0L);
    }

    /**
     * �iget�Ǘ���j<BR>
     * <BR>
     * �w�萔�ʕ��̊Ǘ�����v�Z����B<BR>
     * <BR>
     * this.get�Ǘ���(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̊Ǘ�����擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getManagementFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getManagementFee(l_dblQuantity, 0L);
    }

    /**
     * �iget�Ǘ������Łj<BR>
     * <BR>
     * �w�萔�ʕ��̊Ǘ������ł��v�Z����B<BR>
     * <BR>
     * this.get�Ǘ�������(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̊Ǘ������ł��擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getManagementFeeTax(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFeeTax(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getManagementFeeTax(l_dblQuantity, 0L);
    }

    /**
     * �iget�������j<BR>
     * <BR>
     * �w�萔�ʕ��̏��������v�Z����B<BR>
     * <BR>
     * this.get������(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̏��������擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getInterestFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInterestFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getInterestFee(l_dblQuantity, 0L);
    }

    /**
     * �iget�t�����j<BR>
     * <BR>
     * �w�萔�ʕ��̋t�������v�Z����B<BR>
     * <BR>
     * this.get�t����(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̋t�������擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getPayInterestFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPayInterestFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getPayInterestFee(l_dblQuantity, 0L);
    }

    /**
     * �iget�݊����j<BR>
     * <BR>
     * �w�萔�ʕ��̑݊������v�Z����B<BR>
     * <BR>
     * this.get�݊���(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̑݊������擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getLoanEquityFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLoanEquityFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getLoanEquityFee(l_dblQuantity, 0L);
    }

    /**
     * �iget���̑��j<BR>
     * <BR>
     * �w�萔�ʕ��̂��̑����v�Z����B<BR>
     * <BR>
     * this.get���̑�(��������, �����P��ID)��delegate����B<BR>
     * ���������ʂɂ͈����̐��ʁA�����P��ID�ɂ�0�i���V�K�����j���Z�b�g���A<BR>
     * �������ϕ��̂��̑����擾����B<BR>
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getOther(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOther(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getOther(l_dblQuantity, 0L);
    }

    /**
     * (get�i���X�s��ٍϕʁj�戵����)<BR>
     * ���g�̃v���p�e�B���w�肵<BR>�A
     * �Y������u�i���X�s��ٍϕʁj�戵�����v�I�u�W�F�N�g���擾����B<BR>
     * ���Y�����郌�R�[�h�́u�戵�\�v��false�̏ꍇ�́A<BR>
     * ���u�Y�����R�[�h�Ȃ��v�Ɠ��������Ƃ���B<BR>
     * <BR>
     * �P�j�@@this.����ID �ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�i�،���ЃR�[�h�A���X�R�[�h�̎擾�j<BR>
     * <BR>
     * �Q�j�@@this.�s��ID �ɊY������s��I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�i�s��R�[�h�̎擾�j<BR>
     * <BR>
     * �R�j�@@�i���X�s��ٍϕʁj�戵����(�،���ЃR�[�h, ���X�R�[�h, �s��R�[�h, this.�ٍϋ敪,<BR> 
     * �@@�@@�@@this.�ٍϊ����l)�i�R���X�g���N�^�j���R�[�����A<BR>
     * �@@�@@�@@�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �S�j�@@�R�j�ŊY���f�[�^�Ȃ��A<BR>
     * �@@�@@�@@�܂��͎擾�����i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g.is�戵�\( )��false�̏ꍇ�A<BR>
     * �@@�@@�@@null��Ԃ��B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A�擾�����i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g��Ԃ��B<BR>
     * @@return WEB3GentradeBranchMarketRepayDealtCond
     * @@throws WEB3BaseException
     * @@roseuid 40C4093600E6
     */
    public WEB3GentradeBranchMarketRepayDealtCond getBranchMarketRepayDealtCond() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchMarketRepayDealtCond()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeBranchMarketRepayDealtCond l_branchmarketRepayDealtCond = null;
        //�P�j�@@this.����ID �ɊY������ڋq�I�u�W�F�N�g���擾����B
        String l_strinstitutionCode = this.getSubAccount().getInstitution().getInstitutionCode();
        String l_strbranchCode = this.getSubAccount().getMainAccount().getBranch().getBranchCode();
        try
        {
            //�Q�j�@@this.�s��ID �ɊY������s��I�u�W�F�N�g���擾����B
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(this.getMarketId());
            String l_strmarketCode = l_market.getMarketCode();
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();

            //�i���X�s��ٍϕʁj�戵����(�،���ЃR�[�h, ���X�R�[�h, �s��R�[�h, this.�ٍϋ敪, this.�ٍϊ����l)�i�R���X�g���N�^�j���R�[�����A
            //�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g���擾����B
            l_branchmarketRepayDealtCond =
                new WEB3GentradeBranchMarketRepayDealtCond(l_strinstitutionCode, l_strbranchCode, l_strmarketCode, l_eqtypeContractRow.getRepaymentType(), l_eqtypeContractRow.getRepaymentNum());
            //�S�j�@@�R�j�ŊY���f�[�^�Ȃ��A
            //�܂��͎擾�����i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g.is�戵�\( )��false�̏ꍇ�A
            //null��Ԃ��B
            if (!l_branchmarketRepayDealtCond.isHandlingPossible())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

        }
        catch (DataFindException l_dfe)
        {
            String l_strMessage = "�s��I�u�W�F�N�g�e�[�u�������� error";
            log.error(l_strMessage, l_dfe);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dfe.getMessage(), l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "�s��I�u�W�F�N�g�e�[�u�������� error";
            log.error(l_strMessage, l_dqe);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "�s��I�u�W�F�N�g�e�[�u�������� error";
            log.error(l_strMessage, l_dne);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
        catch (WEB3SystemLayerException l_sle)
        {
            log.error(STR_METHOD_NAME, l_sle);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_sle.getMessage(), l_sle);
        }
        log.exiting(STR_METHOD_NAME);
        return l_branchmarketRepayDealtCond;
    }

    /**
     * �iget�]�����v�i�������o��l���j�j<BR>
     * <BR>
     * �w��P���ŁA�w�萔�ʂ�ԍς����ꍇ�̕]�����v�i�������o��̍l������j���擾����B<BR>
     * <BR>
     * this.get�]�����v�i�������o��l���j(�v�Z�P��, ����, �����P��ID)��<BR>
     * delegate����B<BR>
     * <BR>
     * -------------------------------------------<BR>
     * ��get�]�����v�i�������o��l���j(�v�Z�P��, ����, �����P��ID)�F<BR>
     * �@@�@@�����ݒ�d�l��<BR>
     * <BR>
     * �v�Z�P���F�@@�����̌v�Z�P��<BR>
     * ���ʁF�@@�����̐���<BR>
     * �����P��ID�F�@@0�i���V�K�����j<BR>
     * -------------------------------------------<BR>
     * <BR>
     * @@param l_dblCalcUnitPrice - �v�Z�P���B<BR>
     * @@param l_dblQuantity - ����<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getAppraisalProfitOrLossExpenses(
        double l_dblCalcUnitPrice,
        double l_dblQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAppraisalProfitOrLossExpenses(double, double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getAppraisalProfitOrLossExpenses(l_dblCalcUnitPrice, l_dblQuantity, 0L);
    }

    /**
     * �iget�]�����v�i�������o��l���j�j<BR>
     * <BR>
     * �w��P���ŁA�w�萔�ʂ�ԍς����ꍇ�̕]�����v�i�������o��̍l������j���擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * ��this.isLong( )==true�i�����j�̏ꍇ<BR>
     * �i�p�����[�^.�v�Z�P�� �~ �p�����[�^.���ʁj �| this.get�����(�p�����[�^.����)�@@�|�@@�������o��(*1)<BR>
     * <BR>
     * ��this.isLong( )==false�i�����j�̏ꍇ<BR>
     * this.get�����(�p�����[�^.����) �| �i�p�����[�^.�v�Z�P�� �~ �p�����[�^.���ʁj�@@�|�@@�������o��(*1)<BR>
     * <BR>
     * (*1)�������o��<BR>
     * �����v�Z�T�[�r�X.calc���o��( )�̖߂�l��ݒ肷��B<BR>
     * -------------------------------------------<BR>
     * ��calc���o��( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �ϑ��萔���A�ϑ��萔������ŁF�@@0�i���ώ萔���͊܂߂Ȃ��j<BR>
     * <BR>
     * �ȉ��A�������o��ڂ́A���N���X�̌������o��擾���\�b�h���擾����B<BR>
     * --------------------------<BR>
     * ���萔���F�@@this.get���萔��()�̖߂�l<BR>
     * ���萔������ŁF�@@this.get���萔�������()�̖߂�l<BR>
     * ���`�������F�@@this.get���`������()�̖߂�l<BR>
     * ���`����������ŁF�@@this.get���`�����������(0)�̖߂�l<BR>
     * �Ǘ���F�@@this.get�Ǘ���()�̖߂�l<BR>
     * �Ǘ������ŁF�@@this.get�Ǘ�������()�̖߂�l<BR>
     * �������F�@@this.get������()�̖߂�l<BR>
     * �t�����F�@@this.get�t����()�̖߂�l<BR>
     * �݊����F�@@this.get�݊���()�̖߂�l<BR>
     * ���̑��F�@@this.get���̑�()�̖߂�l<BR>
     * <BR>
     * ���������o��擾���\�b�h�ithis.getXX( )���\�b�h�j�̈����́A�ȉ��̒ʂ�ɐݒ肷��B<BR>
     * <BR>
     * �@@�@@�������ʁF�@@�p�����[�^.����<BR>
     * �@@�@@�����P��ID�F�@@�p�����[�^.�����P��ID<BR>
     * --------------------------<BR>
     * <BR>
     * ���敪�F�@@this.���敪<BR>
     * -------------------------------------------<BR>
     * <BR>
     * @@param l_dblCalcUnitPrice - �v�Z�P���B<BR>
     * @@param l_dblQuantity - ����<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getAppraisalProfitOrLossExpenses(
        double l_dblCalcUnitPrice,
        double l_dblQuantity,
        long l_orderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAppraisalProfitOrLossExpenses(double, double, long)";
        log.entering(STR_METHOD_NAME);

        double l_dblReturnValue = 0.0D;

        // �������o��̌v�Z���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_equityBizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)this.getDataSourceObject();
        double l_dblexpenses =
            l_equityBizLogicProvider.calcExpenses(
                0,
                0,
                getSetupFee(l_dblQuantity, l_orderUnitId),
                getSetupFeeTax(l_dblQuantity, l_orderUnitId),
                getNameTransferFee(l_dblQuantity, l_orderUnitId),
                getNameTransferFeeTax(l_dblQuantity, l_orderUnitId),
                getManagementFee(l_dblQuantity, l_orderUnitId),
                getManagementFeeTax(l_dblQuantity, l_orderUnitId),
                getInterestFee(l_dblQuantity, l_orderUnitId),
                getPayInterestFee(l_dblQuantity, l_orderUnitId),
                getLoanEquityFee(l_dblQuantity, l_orderUnitId),
                getOther(l_dblQuantity, l_orderUnitId),
                l_eqtypeContractRow.getContractType());

        // ��this.isLong( )==true�i�����j�̏ꍇ
        // �i�p�����[�^.�v�Z�P�� �~ �p�����[�^.���ʁj �| this.get�����(�p�����[�^.����)�@@�|�@@�������o��(*1)
        //
        // ��this.isLong( )==false�i�����j�̏ꍇ
        // this.get�����(�p�����[�^.����) �| �i�p�����[�^.�v�Z�P�� �~ �p�����[�^.���ʁj�@@�|�@@�������o��(*1)
        if (this.isLong())
        {
            l_dblReturnValue = (l_dblCalcUnitPrice * l_dblQuantity) - this.getContractAmount(l_dblQuantity) - l_dblexpenses;
        }
        else
        {
            l_dblReturnValue = this.getContractAmount(l_dblQuantity) - (l_dblCalcUnitPrice * l_dblQuantity) - l_dblexpenses;
        }

        log.exiting(STR_METHOD_NAME);
        return new BigDecimal(l_dblReturnValue).longValue();
    }

    /**
     * �iget���萔���j<BR>
     * <BR>
     * �w�萔�ʕ��̌��萔�����v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̌��萔���{���ύϕ�(*2)�̌��萔���j��Ԃ��B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
     * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̌��萔�����擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����c���F�@@this.������<BR>
     * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�����ϕ��̌��萔�����v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@���萔���i�����ϕ��j = this.���萔�� * �擾�������䗦�ifactor�j<BR>
     * �@@�@@�@@�@@���~�����؎̂āB<BR>
     * <BR>
     * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̌��萔�����擾����B<BR>
     * <BR>
     * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B <BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̌��萔�����W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���萔���i���ύϕ��j += �g�����U�N�V����.���萔��<BR>
     * <BR>
     * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͌��萔���i���ύϕ��j��0�ƂȂ� �B<BR>
     * <BR>
     * �S�j�@@�i���萔���i�����ϕ��j + ���萔���i���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getSetupFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSetupFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̌��萔�����擾����B
        double l_dblSetupFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̌��萔�����v�Z����B
            //
            // �@@�@@�@@�@@���萔���i�����ϕ��j = this.���萔�� * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            l_dblSetupFeeOpen = Math.floor(this.getSetupFee() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̌��萔�����擾����B
        double l_dblSetupFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̌��萔�����W�v����B
            //
            // �@@�@@�@@�@@�@@���萔���i���ύϕ��j += �g�����U�N�V����.���萔��
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͌��萔���i���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblSetupFeeClose += l_finTransaction.getImportedSetupFee();
            }
        }

        // �S�j�@@�i���萔���i�����ϕ��j + ���萔���i���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblSetupFeeOpen + l_dblSetupFeeClose;
    }

   /**
    * �iget���萔������Łj<BR>
    * <BR>
    * �w�萔�ʕ��̌��萔������ł��v�Z����B<BR>
    * �|�i�����ϕ�(*1)�̌��萔������Ł{���ύϕ�(*2)�̌��萔������Łj��Ԃ��B<BR>
    * �@@�@@-------------------------------<BR>
    * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
    * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
    * �@@�@@-------------------------------<BR>
    * <BR>
    * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
    * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
    * <BR>
    * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
    * <BR>
    * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̌��萔������ł��擾����B<BR>
    * <BR>
    * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
    * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
    * <BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
    * <BR>
    * �@@�@@�@@�����c���F�@@this.������<BR>
    * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * <BR>
    * �Q�|�Q�j�@@�����ϕ��̌��萔������ł��v�Z����B<BR>
    * <BR>
    * �@@�@@�@@�@@���萔������Łi�����ϕ��j = this.���萔������� * �擾�������䗦�ifactor�j<BR>
    * �@@�@@�@@�@@���~�����؎̂āB<BR>
    * <BR>
    * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̌��萔������ł��擾����B<BR>
    * <BR>
    * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
    * �@@�@@�@@�@@�@@�擾����B <BR>
    * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
    * <BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
    * <BR>
    * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
    * �@@�@@�@@����ID�F�@@this.����ID<BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * <BR>
    * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̌��萔������ł��W�v����B<BR>
    * <BR>
    * �@@�@@�@@�@@�@@���萔������Łi���ύϕ��j += �g�����U�N�V����.���萔�������<BR>
    * <BR>
    * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͌��萔������Łi���ύϕ��j��0�ƂȂ� �B<BR>
    * <BR>
    * �S�j�@@�i���萔������Łi�����ϕ��j + ���萔������Łi���ύϕ��j�j��ԋp����B<BR>
    * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getSetupFeeTax(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSetupFeeTax(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̌��萔������ł��擾����B
        double l_dblSetupFeeTaxOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̌��萔������ł��v�Z����B
            //
            // �@@�@@�@@�@@���萔������Łi�����ϕ��j = this.���萔������� * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            l_dblSetupFeeTaxOpen = Math.floor(this.getSetupFeeTax() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̌��萔������ł��擾����B
        double l_dblSetupFeeTaxClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̌��萔������ł��W�v����B
            //
            // �@@�@@�@@�@@�@@���萔������Łi���ύϕ��j += �g�����U�N�V����.���萔�������
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͌��萔������Łi���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblSetupFeeTaxClose += l_finTransaction.getImportedSetupFeeTax();
            }
        }

        // �S�j�@@�i���萔������Łi�����ϕ��j + ���萔������Łi���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblSetupFeeTaxOpen + l_dblSetupFeeTaxClose;
    }

    /**
     * �iget���`�������j<BR>
     * <BR>
     * �w�萔�ʕ��̖��`���������v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̖��`�������{���ύϕ�(*2)�̖��`�������j��Ԃ��B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
     * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̖��`���������擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����c���F�@@this.������<BR>
     * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�����ϕ��̖��`���������v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@���`�������i�����ϕ��j = this.���`������ * �擾�������䗦�ifactor�j<BR>
     * �@@�@@�@@�@@���~�����؎̂āB<BR>
     * <BR>
     * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̖��`���������擾����B<BR>
     * <BR>
     * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B <BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̖��`���������W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���`�������i���ύϕ��j += �g�����U�N�V����.���`������<BR>
     * <BR>
     * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͖��`�������i���ύϕ��j��0�ƂȂ� �B<BR>
     * <BR>
     * �S�j�@@�i���`�������i�����ϕ��j + ���`�������i���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getNameTransferFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNameTransferFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̖��`���������擾����B
        double l_dblNameTransferFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̖��`���������v�Z����B
            //
            // �@@�@@�@@�@@���`�������i�����ϕ��j = this.���`������ * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblNameTransferFeeOpen = Math.floor(l_eqtypeContractRow.getNameTransferFee() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̖��`���������擾����B
        double l_dblNameTransferFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̖��`���������W�v����B
            //
            // �@@�@@�@@�@@�@@���`�������i���ύϕ��j += �g�����U�N�V����.���`������
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͖��`�������i���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblNameTransferFeeClose += l_finTransaction.getImportedNameTransferFee();
            }
        }

        // �S�j�@@�i���`�������i�����ϕ��j + ���`�������i���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblNameTransferFeeOpen + l_dblNameTransferFeeClose;
    }

   /**
    * �iget���`����������Łj<BR>
    * <BR>
    * �w�萔�ʕ��̖��`����������ł��v�Z����B<BR>
    * �|�i�����ϕ�(*1)�̖��`����������Ł{���ύϕ�(*2)�̖��`����������Łj��Ԃ��B<BR>
    * �@@�@@-------------------------------<BR>
    * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
    * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
    * �@@�@@-------------------------------<BR>
    * <BR>
    * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
    * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
    * <BR>
    * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
    * <BR>
    * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̖��`����������ł��擾����B<BR>
    * <BR>
    * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
    * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
    * <BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
    * <BR>
    * �@@�@@�@@�����c���F�@@this.������<BR>
    * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * <BR>
    * �Q�|�Q�j�@@�����ϕ��̖��`����������ł��v�Z����B<BR>
    * <BR>
    * �@@�@@�@@�@@���`����������Łi�����ϕ��j = this.���`����������� * �擾�������䗦�ifactor�j<BR>
    * �@@�@@�@@�@@���~�����؎̂āB<BR>
    * <BR>
    * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̖��`����������ł��擾����B<BR>
    * <BR>
    * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
    * �@@�@@�@@�@@�@@�擾����B <BR>
    * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
    * <BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
    * <BR>
    * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
    * �@@�@@�@@����ID�F�@@this.����ID<BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * <BR>
    * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̖��`����������ł��W�v����B<BR>
    * <BR>
    * �@@�@@�@@�@@�@@���`����������Łi���ύϕ��j += �g�����U�N�V����.���`�����������<BR>
    * <BR>
    * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͖��`����������Łi���ύϕ��j��0�ƂȂ� �B<BR>
    * <BR>
    * �S�j�@@�i���`����������Łi�����ϕ��j + ���`����������Łi���ύϕ��j�j��ԋp����B<BR>
    * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getNameTransferFeeTax(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNameTransferFeeTax(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̖��`����������ł��擾����B
        double l_dblNameTransferFeeTaxOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̖��`����������ł��v�Z����B
            //
            // �@@�@@�@@�@@���`����������Łi�����ϕ��j = this.���`����������� * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblNameTransferFeeTaxOpen = Math.floor(l_eqtypeContractRow.getNameTransferFeeTax() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̖��`����������ł��擾����B
        double l_dblNameTransferFeeTaxClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̖��`����������ł��W�v����B
            //
            // �@@�@@�@@�@@�@@���`����������Łi���ύϕ��j += �g�����U�N�V����.���`�����������
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͖��`����������Łi���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblNameTransferFeeTaxClose += l_finTransaction.getImportedNameTransferFeeTax();
            }
        }

        // �S�j�@@�i���`����������Łi�����ϕ��j + ���`����������Łi���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblNameTransferFeeTaxOpen + l_dblNameTransferFeeTaxClose;
    }

    /**
     * �iget�Ǘ���j<BR>
     * <BR>
     * �w�萔�ʕ��̊Ǘ�����v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̊Ǘ���{���ύϕ�(*2)�̊Ǘ���j��Ԃ��B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
     * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̊Ǘ�����擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����c���F�@@this.������<BR>
     * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�����ϕ��̊Ǘ�����v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�Ǘ���i�����ϕ��j = this.�Ǘ��� * �擾�������䗦�ifactor�j<BR>
     * �@@�@@�@@�@@���~�����؎̂āB<BR>
     * <BR>
     * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̊Ǘ�����擾����B<BR>
     * <BR>
     * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B <BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̊Ǘ�����W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�Ǘ���i���ύϕ��j += �g�����U�N�V����.�Ǘ���<BR>
     * <BR>
     * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͊Ǘ���i���ύϕ��j��0�ƂȂ� �B<BR>
     * <BR>
     * �S�j�@@�i�Ǘ���i�����ϕ��j + �Ǘ���i���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getManagementFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̊Ǘ�����擾����B
        double l_dblManagementFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̊Ǘ�����v�Z����B
            //
            // �@@�@@�@@�@@�Ǘ���i�����ϕ��j = this.�Ǘ��� * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            l_dblManagementFeeOpen = Math.floor(this.getManagementFee() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̊Ǘ�����擾����B
        double l_dblManagementFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̊Ǘ�����W�v����B
            //
            // �@@�@@�@@�@@�@@�Ǘ���i���ύϕ��j += �g�����U�N�V����.�Ǘ���
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͊Ǘ���i���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblManagementFeeClose += l_finTransaction.getImportedManagementFee();
            }
        }

        // �S�j�@@�i�Ǘ���i�����ϕ��j + �Ǘ���i���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblManagementFeeOpen + l_dblManagementFeeClose;
    }

   /**
    * �iget�Ǘ������Łj<BR>
    * <BR>
    * �w�萔�ʕ��̊Ǘ������ł��v�Z����B<BR>
    * �|�i�����ϕ�(*1)�̊Ǘ������Ł{���ύϕ�(*2)�̊Ǘ������Łj��Ԃ��B<BR>
    * �@@�@@-------------------------------<BR>
    * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
    * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
    * �@@�@@-------------------------------<BR>
    * <BR>
    * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
    * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
    * <BR>
    * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
    * <BR>
    * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̊Ǘ������ł��擾����B<BR>
    * <BR>
    * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
    * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
    * <BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
    * <BR>
    * �@@�@@�@@�����c���F�@@this.������<BR>
    * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * <BR>
    * �Q�|�Q�j�@@�����ϕ��̊Ǘ������ł��v�Z����B<BR>
    * <BR>
    * �@@�@@�@@�@@�Ǘ������Łi�����ϕ��j = this.�Ǘ������� * �擾�������䗦�ifactor�j<BR>
    * �@@�@@�@@�@@���~�����؎̂āB<BR>
    * <BR>
    * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̊Ǘ������ł��擾����B<BR>
    * <BR>
    * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
    * �@@�@@�@@�@@�@@�擾����B <BR>
    * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
    * <BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
    * <BR>
    * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
    * �@@�@@�@@����ID�F�@@this.����ID<BR>
    * �@@�@@�@@----------------------------------------------------------<BR>
    * <BR>
    * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̊Ǘ������ł��W�v����B<BR>
    * <BR>
    * �@@�@@�@@�@@�@@�Ǘ������Łi���ύϕ��j += �g�����U�N�V����.�Ǘ�������<BR>
    * <BR>
    * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͊Ǘ������Łi���ύϕ��j��0�ƂȂ� �B<BR>
    * <BR>
    * �S�j�@@�i�Ǘ������Łi�����ϕ��j + �Ǘ������Łi���ύϕ��j�j��ԋp����B<BR>
    * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getManagementFeeTax(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFeeTax(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̊Ǘ������ł��擾����B
        double l_dblManagementFeeTaxOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̊Ǘ������ł��v�Z����B
            //
            // �@@�@@�@@�@@�Ǘ������Łi�����ϕ��j = this.�Ǘ������� * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            l_dblManagementFeeTaxOpen = Math.floor(this.getManagementFeeTax() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̊Ǘ������ł��擾����B
        double l_dblManagementFeeTaxClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̊Ǘ������ł��W�v����B
            //
            // �@@�@@�@@�@@�@@�Ǘ������Łi���ύϕ��j += �g�����U�N�V����.�Ǘ�������
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͊Ǘ������Łi���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblManagementFeeTaxClose += l_finTransaction.getImportedManagementFeeTax();
            }
        }

        // �S�j�@@�i�Ǘ������Łi�����ϕ��j + �Ǘ������Łi���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblManagementFeeTaxOpen + l_dblManagementFeeTaxClose;
    }

    /**
     * �iget�������j<BR>
     * <BR>
     * �w�萔�ʕ��̏��������v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̏������{���ύϕ�(*2)�̏������j��Ԃ��B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
     * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̏��������擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����c���F�@@this.������<BR>
     * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�����ϕ��̏��������v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������i�����ϕ��j = this.������ * �擾�������䗦�ifactor�j<BR>
     * �@@�@@�@@�@@���~�����؎̂āB<BR>
     * <BR>
     * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̏��������擾����B<BR>
     * <BR>
     * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B <BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̏��������W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������i���ύϕ��j += �g�����U�N�V����.������<BR>
     * <BR>
     * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͏������i���ύϕ��j��0�ƂȂ� �B<BR>
     * <BR>
     * �S�j�@@�i�������i�����ϕ��j + �������i���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getInterestFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInterestFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̏��������擾����B
        double l_dblInterestFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̏��������v�Z����B
            //
            // �@@�@@�@@�@@�������i�����ϕ��j = this.������ * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            l_dblInterestFeeOpen = Math.floor(this.getInterestFee() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̏��������擾����B
        double l_dblInterestFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̏��������W�v����B
            //
            // �@@�@@�@@�@@�@@�������i���ύϕ��j += �g�����U�N�V����.������
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͏������i���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblInterestFeeClose += l_finTransaction.getImportedInterestFee();
            }
        }

        // �S�j�@@�i�������i�����ϕ��j + �������i���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblInterestFeeOpen + l_dblInterestFeeClose;
    }

    /**
     * �iget�t�����j<BR>
     * <BR>
     * �w�萔�ʕ��̋t�������v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̋t�����{���ύϕ�(*2)�̋t�����j��Ԃ��B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
     * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̋t�������擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����c���F�@@this.������<BR>
     * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�����ϕ��̋t�������v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�t�����i�����ϕ��j = this.�t���� * �擾�������䗦�ifactor�j<BR>
     * �@@�@@�@@�@@���~�����؎̂āB<BR>
     * <BR>
     * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̋t�������擾����B<BR>
     * <BR>
     * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B <BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̋t�������W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�t�����i���ύϕ��j += �g�����U�N�V����.�t����<BR>
     * <BR>
     * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͋t�����i���ύϕ��j��0�ƂȂ� �B<BR>
     * <BR>
     * �S�j�@@�i�t�����i�����ϕ��j + �t�����i���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getPayInterestFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "PayInterestFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̋t�������擾����B
        double l_dblPayInterestFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̋t�������v�Z����B
            //
            // �@@�@@�@@�@@�t�����i�����ϕ��j = this.�t���� * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblPayInterestFeeOpen = Math.floor(l_eqtypeContractRow.getPayInterestFee() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̋t�������擾����B
        double l_dblPayInterestFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̋t�������W�v����B
            //
            // �@@�@@�@@�@@�@@�t�����i���ύϕ��j += �g�����U�N�V����.�t����
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͋t�����i���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblPayInterestFeeClose += l_finTransaction.getImportedPayInterestFee();
            }
        }

        // �S�j�@@�i�t�����i�����ϕ��j + �t�����i���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblPayInterestFeeOpen + l_dblPayInterestFeeClose;
    }

    /**
     * �iget�݊����j<BR>
     * <BR>
     * �w�萔�ʕ��̑݊������v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̑݊����{���ύϕ�(*2)�̑݊����j��Ԃ��B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �O�j�@@this.isLong( )==true�i�����j�̏ꍇ�́A0��ԋp����B<BR>
     *       this.isLong( )==false�i�����j�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
     * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̑݊������擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����c���F�@@this.������<BR>
     * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�����ϕ��̑݊������v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�݊����i�����ϕ��j = this.�݊��� * �擾�������䗦�ifactor�j<BR>
     * �@@�@@�@@�@@���~�����؎̂āB<BR>
     * <BR>
     * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̑݊������擾����B<BR>
     * <BR>
     * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B <BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̑݊������W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�݊����i���ύϕ��j += �g�����U�N�V����.�݊���<BR>
     * <BR>
     * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͑݊����i���ύϕ��j��0�ƂȂ� �B<BR>
     * <BR>
     * �S�j�@@�i�݊����i�����ϕ��j + �݊����i���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getLoanEquityFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "LoanEquityFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // �O�j�@@this.isLong( )==true�i�����j�̏ꍇ�́A0��ԋp����B
        if (this.isLong())
        {
            return 0.0D;
        }

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̑݊������擾����B
        double l_dblLoanEquityFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̑݊������v�Z����B
            //
            // �@@�@@�@@�@@�݊����i�����ϕ��j = this.�݊��� * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblLoanEquityFeeOpen = Math.floor(l_eqtypeContractRow.getLoanEquityFee() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̑݊������擾����B
        double l_dblLoanEquityFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̑݊������W�v����B
            //
            // �@@�@@�@@�@@�@@�݊����i���ύϕ��j += �g�����U�N�V����.�݊���
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͑݊����i���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblLoanEquityFeeClose += l_finTransaction.getImportedLoanEquityFee();
            }
        }

        // �S�j�@@�i�݊����i�����ϕ��j + �݊����i���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblLoanEquityFeeOpen + l_dblLoanEquityFeeClose;
    }

    /**
     * �iget���̑��j<BR>
     * <BR>
     * �w�萔�ʕ��̂��̑����v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̂��̑��{���ύϕ�(*2)�̂��̑��j��Ԃ��B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B������v�Z�ɂ��擾�B<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR>
     * �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@this.get�ԍϖ��ϐ���(�����̒����P��ID)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̂��̑����擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���o����䗦( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����c���F�@@this.������<BR>
     * �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�����ϕ��̂��̑����v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@���̑��i�����ϕ��j = this.���̑� * �擾�������䗦�ifactor�j<BR>
     * �@@�@@�@@�@@���~�����؎̂āB<BR>
     * <BR>
     * �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̂��̑����擾����B<BR>
     * <BR>
     * �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B <BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̂��̑����W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���̑��i���ύϕ��j += �g�����U�N�V����.���̑�<BR>
     * <BR>
     * �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͂��̑��i���ύϕ��j��0�ƂȂ� �B<BR>
     * <BR>
     * �S�j�@@�i���̑��i�����ϕ��j + ���̑��i���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getOther(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "Other(double, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A
        // �@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // �Q�j�@@this.������ > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̂��̑����擾����B
        double l_dblOtherOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // �Q�|�P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
            //      �@@�@@�������|�W�V�����}�l�[�W��.calc���o����䗦( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����c���F�@@this.������<BR>
            // �@@�@@�@@���ϖ�萔�ʁF�@@�i�����̒������� - �P�j�̌��ϖ�萔�ʁj�@@�������ϐ��ʂ��Z�b�g�B
            // �@@�@@�@@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // �Q�|�Q�j�@@�����ϕ��̂��̑����v�Z����B
            //
            // �@@�@@�@@�@@���̑��i�����ϕ��j = this.���̑� * �擾�������䗦�ifactor�j
            // �@@�@@�@@�@@���~�����؎̂āB
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblOtherOpen = Math.floor(l_eqtypeContractRow.getOther() * l_dblFactor);
        }
        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i����肠��j�̏ꍇ�A���ύϕ��̂��̑����擾����B
        double l_dblOtherClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // �R�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
            // �@@�@@�@@�@@�@@�擾����B 
            // �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
            //
            // �@@�@@�@@----------------------------------------------------------
            // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
            //
            // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
            // �@@�@@�@@����ID�F�@@this.����ID
            // �@@�@@�@@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̂��̑����W�v����B
            //
            // �@@�@@�@@�@@�@@���̑��i���ύϕ��j += �g�����U�N�V����.���̑�
            //
            // �@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͂��̑��i���ύϕ��j��0�ƂȂ� �B
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblOtherClose += l_finTransaction.getImportedOther();
            }
        }

        // �S�j�@@�i���̑��i�����ϕ��j + ���̑��i���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblOtherOpen + l_dblOtherClose;
    }

    /**
     * (get�Ǘ���)<BR>
     * �w�蒍���̊Ǘ�����v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̊Ǘ���{���ύϕ�(*2)�̊Ǘ���j��Ԃ��B<BR>
     * �@@�@@�o���ʒm�i���^������j����Ă΂��ꍇ�A<BR>
     * �@@�@@�����ϕ��Ɍv�コ���P�[�X�����ύϕ��Ɍv�コ���P�[�X������̂�<BR>
     * �@@�@@���Z�����l��Ԃ��d�l�Ƃ���B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B���擾�B�i�����Ȃ��j<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �P�j�@@�����ϕ��̊Ǘ�����擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�Ǘ���i�����ϕ��j = this.�Ǘ���<BR>
     * <BR>
     * �Q�j�@@���ύϕ��̊Ǘ�����擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B <BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̊Ǘ�����W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�Ǘ���i���ύϕ��j += �g�����U�N�V����.�Ǘ���<BR>
     * <BR>
     * �@@�@@�@@���g�����U�N�V����0������0�Ƃ��� �B<BR>
     * <BR>
     * �R�j�@@�i�Ǘ���i�����ϕ��j + �Ǘ���i���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * �����P��ID�B<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getManagementFee(
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFee(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����ϕ��̊Ǘ�����擾����B
        //�Ǘ���i�����ϕ��j = this.�Ǘ���
        double l_dblManagementFeeOpen = 0.0D;
        l_dblManagementFeeOpen = this.getManagementFee();

        //�Q�j�@@���ύϕ��̊Ǘ�����擾����B
        //�Q�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
        //           �擾����B 
        //           �������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
        //         ��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
        //          �����P��ID�F�@@�����̒����P��ID
        //          ����ID�F�@@this.����ID
        double l_dblManagementFeeClose = 0.0D;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        List l_lisFinTransactionList =
            l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());

        //�Q�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̊Ǘ�����W�v����B
        //           �Ǘ���i���ύϕ��j += �g�����U�N�V����.�Ǘ���
        //          ���g�����U�N�V����0������0�Ƃ��� �B
        int l_intRowSize = l_lisFinTransactionList.size();
        for (int i = 0; i < l_intRowSize; i++)
        {
            EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow)l_lisFinTransactionList.get(i);
            l_dblManagementFeeClose += l_finTransactionRow.getImportedManagementFee();
        }

        // �R�j�@@�i�Ǘ���i�����ϕ��j + �Ǘ���i���ύϕ��j�j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblManagementFeeOpen + l_dblManagementFeeClose;
    }

    /**
     * (get�Ǘ�������)<BR>
     * �w�蒍���̊Ǘ������ł��v�Z����B<BR>
     * �|�i�����ϕ�(*1)�̊Ǘ������Ł{���ύϕ�(*2)�̊Ǘ������Łj��Ԃ��B<BR>
     * �@@�@@�o���ʒm�i���^������j����Ă΂��ꍇ�A<BR>
     * �@@�@@�����ϕ��Ɍv�コ���P�[�X�����ύϕ��Ɍv�コ���P�[�X������̂� <BR>
     * �@@�@@���Z�����l��Ԃ��d�l�Ƃ���B<BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@(*1)�����̓����v���p�e�B���擾�B�i�����Ȃ��j<BR>
     * �@@�@@(*2)���ϖ��ɂ�茚�����猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �P�j�@@�����ϕ��̊Ǘ������ł��擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�Ǘ������Łi�����ϕ��j = this.�Ǘ�������<BR>
     * <BR>
     * �Q�j�@@���ύϕ��̊Ǘ������ł��擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��<BR>
     * �@@�@@�@@�@@�@@�擾����B<BR>
     * �@@�@@�@@�@@�@@�������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>
     * �@@�@@�@@����ID�F�@@this.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̊Ǘ������ł��W�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�Ǘ������Łi���ύϕ��j += �g�����U�N�V����.�Ǘ�������<BR>
     * <BR>
     * �@@�@@�@@���g�����U�N�V����0������0�Ƃ��� �B<BR>
     * <BR>
     * �R�j�@@�i�Ǘ������Łi�����ϕ��j + �Ǘ������Łi���ύϕ��j�j��ԋp����B<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
     public double getManagementFeeTax(long l_lngOrderUnitId)
         throws WEB3BaseException
     {
         final String STR_METHOD_NAME = "getManagementFeeTax(long)";
         log.entering(STR_METHOD_NAME);

         //�P�j�@@�����ϕ��̊Ǘ������ł��擾����B
         //�Ǘ������Łi�����ϕ��j = this.�Ǘ�������
         double l_dblManagementFeeTaxOpen = this.getManagementFeeTax();

         //�Q�j�@@���ύϕ��̊Ǘ������ł��擾����B
         //�Q�|�P�j���Y�����{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ��
         // �擾����B
         // �������|�W�V�����}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )���擾����B
         // �@@�@@�@@----------------------------------------------------------
         // �@@�@@�@@��get�����ڋq���薾��ListBy�����P��Plus����( )�F�����ݒ�d�l��
         //
         // �@@�@@�@@�����P��ID�F�@@�����̒����P��ID
         // �@@�@@�@@����ID�F�@@this.����ID
         // �@@�@@�@@----------------------------------------------------------

         FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
         TradingModule l_tradingModule =
             l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

         WEB3EquityPositionManager l_positionManager =
             (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

         List l_lisFinTransactionList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(
             l_lngOrderUnitId,
             this.getContractId());

         // �R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̊Ǘ������ł��W�v����B
         //�Ǘ������Łi���ύϕ��j += �g�����U�N�V����.�Ǘ�������
         //�����ϖ�肪�Ȃ��ꍇ�͊Ǘ������Łi���ύϕ��j��0�ƂȂ� �B
         double l_dblManagementFeeTaxClose = 0.0D;
         int l_intRowSize = l_lisFinTransactionList.size();
         for (int i = 0; i < l_intRowSize; i++)
         {
             EqtypeFinTransactionRow l_eqtypeFinTransactionRow = (EqtypeFinTransactionRow)l_lisFinTransactionList.get(i);
             l_dblManagementFeeTaxClose += l_eqtypeFinTransactionRow.getImportedManagementFeeTax();
         }

         // �S�j�@@�i�Ǘ������Łi�����ϕ��j + �Ǘ������Łi���ύϕ��j�j��ԋp����B
         log.exiting(STR_METHOD_NAME);
         return l_dblManagementFeeTaxOpen + l_dblManagementFeeTaxClose;
     } 
}
@
