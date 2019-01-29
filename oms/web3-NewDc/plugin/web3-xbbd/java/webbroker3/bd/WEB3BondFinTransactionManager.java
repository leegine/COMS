head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondFinTransactionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���g�����U�N�V�����}�l�[�W��(WEB3BondFinTransactionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/14 �����(���u) �V�K�쐬         
*/

package webbroker3.bd;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondFinTransactionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpower.data.FixedFinTransactionParams;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (���g�����U�N�V�����}�l�[�W��)<BR>
 * ���g�����U�N�V�����}�l�[�W���N���X
 * 
 * @@author �����
 * @@version 1.0
 */

public class WEB3BondFinTransactionManager extends BondFinTransactionManagerImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondFinTransactionManager.class);
    
    /**
     * (undo�m��g�����U�N�V����By�����P��ID)<BR>
     * ����.�����P��ID�Ɋ֘A����m��g�����U�N�V������undo���� <BR>
     * <BR>
     * �P�j�m��g�����U�N�V�����i�����ڋq���薾�ׁj�e�[�u������������B <BR>
     * �@@�m���������n  <BR>
     * �@@�@@�����P��ID������.�����P��ID  <BR>
     * �@@�@@�����^�C�v�@@����  <BR>
     *  <BR>
     * �Q�j�P�j�Ŏ擾�������R�[�h�������[�v  <BR>
     * �@@�Q�|�P�j���[�v���̊m��g�����U�N�V����Params�̃N���[�����쐬����B <BR>
     * �@@�Q�|�Q�j�N���[���̍폜�t���O��TRUE�ɂ���B  <BR>
     * �@@�Q�|�R�jDB�X�V����������B  <BR>
     *  <BR>
     * @@param l_bondOrderUnit - (�����P��ID)<BR>
     * �����P��ID
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    public void  undoTransactionByOrderUnitId(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "undoTransactionByOrderUnitId(long)";
        log.entering(STR_METHOD_NAME);

        List l_lisFixedFinTransaction = new ArrayList();

        //�P�j�m��g�����U�N�V�����i�����ڋq���薾�ׁj�e�[�u������������B 
        //�m���������n 
        //�����P��ID������.�����P��ID 
        //�����^�C�v�@@���� 
        String l_strWhere = "order_unit_id = ? and product_type = ?";
        Object[] l_bindVars = {new Long(l_lngOrderUnitId), ProductTypeEnum.BOND};
        QueryProcessor l_queryProcessos = null;  
        try
        {
            l_queryProcessos = Processors.getDefaultProcessor();  
            l_lisFixedFinTransaction = 
                l_queryProcessos.doFindAllQuery(
                    FixedFinTransactionRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);    
        }
            
        int l_intSize = 0;
        if (l_lisFixedFinTransaction != null && !l_lisFixedFinTransaction.isEmpty())
        {
            l_intSize = l_lisFixedFinTransaction.size();   
        }
        
        //�Q�j�P�j�Ŏ擾�������R�[�h�������[�v 
        FixedFinTransactionParams l_fixedFinTransactionParams = null;
        for (int i = 0; i < l_intSize; i++)
        {
            //�Q�|�P�j���[�v���̊m��g�����U�N�V����Params�̃N���[�����쐬����B 
            FixedFinTransactionRow l_fixedFinTransactionRow = 
                (FixedFinTransactionRow)l_lisFixedFinTransaction.get(i);
            l_fixedFinTransactionParams = 
                new FixedFinTransactionParams(l_fixedFinTransactionRow);
            //�Q�|�Q�j�N���[���̍폜�t���O��TRUE�ɂ���B
            l_fixedFinTransactionParams.setDeleteFlag(BooleanEnum.TRUE);
            try
            {
                //�Q�|�R�jDB�X�V����������B
                l_queryProcessos.doUpdateQuery(l_fixedFinTransactionParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex);    
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
