head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoClientService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �d�q���V�X�e���ڑ��T�[�r�X�C���^�[�t�F�C�X(WEB3GentradeBatoClientService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
Revesion History : 2008/05/20 ��іQ (���u)���f��No.328,329
Revesion History : 2008/06/17 ��іQ (���u)���f��No.330
*/
package webbroker3.gentrade.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeBatoFunctionDivDef;
import webbroker3.gentrade.define.WEB3GentradeBatoProspectusServiceResultDef;
import webbroker3.gentrade.define.WEB3GentradeBatoServiceRegServiceResultDef;
import webbroker3.gentrade.define.WEB3GentradeBatoTranHistServiceResultDef;
import webbroker3.gentrade.message.WEB3GentradeMultiCheckResults;
import webbroker3.gentrade.message.WEB3GentradeMultiDocCheckResultUnit;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
 * �d�q���V�X�e���ڑ��T�[�r�X�C���^�[�t�F�C�X
 */
public interface WEB3GentradeBatoClientService extends WEB3BusinessService 
{
   
    /**
     * �d�q���V�X�e���ڑ��T�[�r�X�������s���B<br />
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@exception  BUSINESS_ERROR_00013:�@@��t���ԊO
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
     * @@return WEB3GenResponse<br />
     * @@roseuid 421036A8039E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
   
    /**
     * �ivalidate�ژ_�����{���j<br />
     * <br />
     * �ژ_�����{���ς��𔻒肷��B<br />
     * <br />
     * @@param l_typeCode ��ʃR�[�h(�o�q�w���擾)<br />
     * @@param l_productCode �����R�[�h<br />
     * @@return webbroker3.gentrade.message.WEB3GentradeProspectusResult
     * @@see WEB3GentradeProspectusResult
     * @@see WEB3GentradeBatoProspectusServiceResultDef
     * @@exception  BUSINESS_ERROR_00013:�@@��t���ԊO
     * @@exception  BUSINESS_ERROR_01959:�@@�d�q���G���[�@@ 
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
     * @@roseuid 421179480180
     */
    public WEB3GentradeProspectusResult validateProspectus(String l_typeCode, String l_productCode) throws WEB3BaseException;
   
    /**
     * �ivalidate�d�q�����{�j<br />
     * <br />
     * �d�q���V�X�e���Ńp�����[�^�̋@@�\�����{����Ă��邩�𔻒肷��B<br />
     * <br />
     * [�߂�l]<br />
     * ������.�@@�\�敪���h�d�q�������`�F�b�N�h�̏ꍇ<br />
     *   0�F �����ӌڋq<br />
     *   1�F ���ӌڋq<br />
     * ���@@WEB3GentradeBatoServiceRegServiceResultDef�ɂĒ萔��`<br />
     * <br />
     * ������.�@@�\�敪���h����񍐏����{�`�F�b�N�h�̏ꍇ<br />
     *   0�F �����ӌڋq<br />
     *   1�F ���ӌڋq<br />
     *   2�F �����{���<br />
     * ���@@WEB3GentradeBatoTranHistServiceResultDef�ɂĒ萔��`<br />
     * 
     * @@param l_functionDiv  �@@�\�敪(WEB3GentradeBatoFunctionDivDef)
     * @@return �d�q���V�X�e���̖߂�l(WEB3GentradeBatoTranHistServiceResultDef,WEB3GentradeBatoServiceRegServiceResultDef)<br />
     * @@see WEB3GentradeBatoFunctionDivDef
     * @@see WEB3GentradeBatoTranHistServiceResultDef
     * @@see WEB3GentradeBatoServiceRegServiceResultDef
     * @@exception  BUSINESS_ERROR_00013:�@@��t���ԊO
     * @@exception  BUSINESS_ERROR_01959:�@@�d�q���G���[�@@ 
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
     * @@exception  SYSTEM_ERROR_80006:  �����G���[
     * @@roseuid 421179490047
     */
    public String validateBato(String l_functionDiv) throws WEB3BaseException;
    
    /**
     * (is�d�q����~��)<BR>
     * <BR>
     * �d�q���V�X�e������~�����ǂ����𔻒肷��B<BR>
     * <BR>
     * true�F ��~���i��Q���j<BR>
     * false�F �ғ���<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isBatoStopping() throws WEB3BaseException;

    /**
     * (get�d�q���ڑ����)<BR>
     * �d�q���̐ڑ����(URL)���擾���A�ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getBatoConnectionInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;

    /**
     * (validate���������ژ_�����{��)<BR>
     * ���������̖ژ_�����{���ς��𔻒肷��B<BR>
     * <BR>
     * @@param l_multiDocCheckResultUnit - (���������ژ_�����{���`�F�b�N���X�g)<BR>
     * ���������ژ_�����{���`�F�b�N���X�g<BR>
     * @@param l_blnIsCheckFlag - (�㗝���͕s���`�F�b�N�t���O)<BR>
     * �㗝���͕s���ɋƖ��G���[�ɂ��邩���Ȃ����̃t���O<BR>
     * <BR>
     * �㗝���͕s���`�F�b�N�t���O<BR>
     * true�F�`�F�b�N����<BR>
     * false�F�`�F�b�N���Ȃ�<BR>
     * @@return WEB3GentradeMultiCheckResults
     * @@throws WEB3BaseException
     */
    public WEB3GentradeMultiCheckResults validateMultiProspectus(
        WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit,
        boolean l_blnIsCheckFlag) throws WEB3BaseException;
}
@
