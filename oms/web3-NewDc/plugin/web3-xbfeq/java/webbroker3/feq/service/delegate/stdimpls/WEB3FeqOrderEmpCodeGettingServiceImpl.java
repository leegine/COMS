head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderEmpCodeGettingServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������^�p�R�[�h�擾�T�[�r�XImpl�iWEB3FeqOrderEmpCodeGettingServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/03 ���g (���u) �V�K�쐬 ���f��No.501
*/
package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������^�p�R�[�h�擾�T�[�r�XImpl) <BR>
 *  �O�������^�p�R�[�h�擾�T�[�r�XImpl<BR>
 * @@author ���g
 * @@version 1.0 
 */
public class WEB3FeqOrderEmpCodeGettingServiceImpl implements WEB3FeqOrderEmpCodeGettingService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderEmpCodeGettingServiceImpl.class);

    /**
     * (�O�������^�p�R�[�h�擾�T�[�r�XImpl) <BR>
     */
    public WEB3FeqOrderEmpCodeGettingServiceImpl()
    {
    }

    /**
     * (getPREFIX)<BR>
     * �^�p�R�[�h�̍�2�����擾����B<BR>
     * <BR>
     * �P�j�@@�،���ЃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j�@@�،���Ѓv���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���Ђh�c���،���ЃI�u�W�F�N�g.�،����ID<BR>
     * �@@�v���t�@@�����X�� = �hfeq.order.emp.code.div�h<BR>
     * �@@���ږ��A�� = 1<BR>
     * <BR>
     * �R�j�@@�擾�������R�[�h.�v���t�@@�����X�̒l��ԋp����B<BR>
     * <BR>
     * �@@���R�[�h���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPREFIX(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPREFIX(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�،���ЃI�u�W�F�N�g���擾����B
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);

            //�Q�j�@@�،���Ѓv���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B
            //�،���Ђh�c���،���ЃI�u�W�F�N�g.�،����ID
            //�v���t�@@�����X�� = �hfeq.order.emp.code.div�h
            //���ږ��A�� = 1
            InstitutionPreferencesRow l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByPk(
                    l_institution.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.FEQ_ORDER_EMP_CODE_DIV,
                    1);

            log.exiting(STR_METHOD_NAME);
            return l_institutionPreferencesRow.getValue();
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�^�p�R�[�h)<BR>
     * �V���́u�^�p�R�[�h�v��������擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@����.�^�p�R�[�h�I��null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�^�p�R�[�h�̍��Q�����擾����B<BR>
     * <BR>
     * �@@�@@this.getPREFIX�i�j���R�[������B<BR>
     * <BR>
     * �@@�@@�m����]<BR>
     * �@@�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * <BR>
     * �@@�P�|�Q�j�@@7���̉^�p�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@getPREFIX�i�j�̖߂�l�{�����̉^�p�R�[�h<BR>
     * <BR>
     * �@@�P�|�R�j�@@�擾����7���̉^�p�R�[�h��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getEmpCode(String l_strInstitutionCode, String l_strEmpCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEmpCode(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����.�^�p�R�[�h�I��null�̏ꍇ�A�ȉ��̏������s���B
        if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strEmpCode))
        {
            //�P�|�P�j�@@�^�p�R�[�h�̍��Q�����擾����B
            //this.getPREFIX�i�j���R�[������B
            //�،���ЃR�[�h�F ����.�،���ЃR�[�h
            String l_strEmpCodePREFIX = this.getPREFIX(l_strInstitutionCode);

            //�P�|�Q�j�@@7���̉^�p�R�[�h���擾����B
            //getPREFIX�i�j�̖߂�l�{�����̉^�p�R�[�h
            //�P�|�R�j�@@�擾����7���̉^�p�R�[�h��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_strEmpCodePREFIX + l_strEmpCode;
        }

        //�Q�j�@@��L�ȊO�̏ꍇ�Anull��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return null;
    }

}
@
