head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.27.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXExtConnectionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�O���ڑ��}�l�[�W��(WEB3FXExtConnectionManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 �����F (���u) �V�K�쐬 �d�l�ύX�E���f��1172 ���f��1179
Revision History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1206
*/

package webbroker3.aio;

import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�O���ڑ��}�l�[�W��)<BR>
 * FX�O���ڑ��}�l�[�W���N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FXExtConnectionManager
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXExtConnectionManager.class);

    /**
     * (get�O���ڑ��C���X�^���X)<BR>
     * �O���ڑ��C���X�^���X���擾����B<BR>
     * <BR>
     * ����.�O���ڑ��V�X�e���R�[�h���A�O���ڑ��V�X�e���̃C���X�^���X��ԋp����B <BR>
     * �P�|�P�j�O���ڑ��V�X�e���R�[�h�@@���@@01�FGFT�̏ꍇ <BR>
     * �@@�@@GFT�ڑ��V�X�e���̃C���X�^���X��ԋp�B <BR>
     * <BR>
     * �P�|�Q�j�O���ڑ��V�X�e���R�[�h�@@���@@05:�V���v���N�X�̏ꍇ <BR>
     * <BR>
     * �@@�@@�P�|�Q�|�P�j����.�����敪�@@���@@01�F�����J�݂̏ꍇ <BR>
     * �@@�@@�@@�@@�@@Simplex�����J�ݐڑ��V�X�e���̃C���X�^���X��ԋp�B <BR>
     * <BR>
     * �@@�@@�P�|�Q�|�Q�j����.�����敪�@@���@@07�F�U�։\�z�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@Simplex�U�։\�z�ڑ��V�X�e���̃C���X�^���X��ԋp�B <BR>
     * <BR>
     * �@@�@@�P�|�Q�|�R�j����.�����敪�@@���@@02�F������04�F�o���̏ꍇ <BR>
     * �@@�@@�@@�@@�@@��O�u�����敪���s���ł��B�v���X���[����B <BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03184<BR>
     * <BR>
     * �P�|�R�j�ȏ�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03161<BR>
     * <BR>
     * @@param l_strExtConnectionSystemCode - (�O���ڑ��V�X�e���R�[�h)<BR>
     * �O���ڑ��V�X�e���R�[�h<BR>
     * @@param l_strOperationDiv - (�����敪)<BR>
     * �����敪<BR>
     * @@return WEB3ExtConnection
     * @@throws WEB3BaseException
     */
    public static WEB3ExtConnection getExtConnectionInstance(String l_strExtConnectionSystemCode, String l_strOperationDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExtConnectionInstance(String, String)";
        log.entering (STR_METHOD_NAME);

        //�O���ڑ��V�X�e���R�[�h�@@���@@01�FGFT�@@�̏ꍇ
        if (WEB3ExtConnectSystemCodeDef.GFT.equals(l_strExtConnectionSystemCode))
        {
            log.exiting(STR_METHOD_NAME);
            return new WEB3GFTConnectionSystem();
        }
        //�O���ڑ��V�X�e���R�[�h�@@���@@05:�V���v���N�X�̏ꍇ
        if (WEB3ExtConnectSystemCodeDef.SIMPLEX.equals(l_strExtConnectionSystemCode))
        {
            //����.�����敪�@@���@@01�F�����J�݂̏ꍇ 
            if (WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals(l_strOperationDiv))
            {
                log.exiting(STR_METHOD_NAME);
                //Simplex�����J�ݐڑ��V�X�e���̃C���X�^���X��ԋp�B
                return new WEB3FXSimplexAccOpenConnectionSystem();
            }
            //����.�����敪�@@���@@07�F�U�։\�z�̏ꍇ
            else if (WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals(l_strOperationDiv))
            {
                log.exiting(STR_METHOD_NAME);
                //Simplex�U�։\�z�ڑ��V�X�e���̃C���X�^���X��ԋp�B
                return new WEB3FXSimplexTransferAbleAmtConnectionSystem();
            }
            //����.�����敪�@@���@@02�F������04�F�o���̏ꍇ
            else if (WEB3AdminAioGftOperationDivDef.CASH_IN.equals(l_strOperationDiv)
                || WEB3AdminAioGftOperationDivDef.CASH_OUT.equals(l_strOperationDiv))
            {
                log.debug("�����敪���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03184,
                    "getExtConnectionInstance(String, String)" + "." + STR_METHOD_NAME,
                    "�����敪���s���ł��B");
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�ȏ�ȊO�̏ꍇ�A��O��throw����
        else
        {
            log.debug("�O���ڑ��V�X�e���R�[�h�̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03161,
                "getExtConnectionInstance(String, String)" + "." + STR_METHOD_NAME,
                "�O���ڑ��V�X�e���R�[�h�̒l���s���ł��B");
        }
    }
}
@
