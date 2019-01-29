head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecFrontOrderCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�t�����g�����Ǘ����ʃT�[�r�X) (WEB3AdminDirSecFrontOrderCommonService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.118
Revesion History : 2007/02/17  ���؎q (���u) �����̖��No.003
Revesion History : 2007/02/27  �Ӑ� (���u) ���f��No.023,042,043,047-049
Revesion History : 2007/02/28  �Ј��� (���u) �d�l�ύX���f��No.059
Revesion History : 2007/03/02  �Ј��� (���u) �d�l�ύX���f��No.095
*/
package webbroker3.dirsec.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.gentrade.data.OrderSwitchingRow;


/**
 * (�Ǘ��҃t�����g�����Ǘ����ʃT�[�r�X)<BR>
 * <BR>
 * �Ǘ��҃t�����g�����Ǘ����ʃT�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * WEB3AdminDirSecFrontOrderCommonService interface<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public interface WEB3AdminDirSecFrontOrderCommonService extends Service {
    /**
     * ������ؑփe�[�u�����A�����ɊY�����郌�R�[�h���擾���A<BR>
     * �ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@return ������ؑ�Row[]<BR>
     * @@roseuid 42D24FD300B0
     */
    public OrderSwitchingRow[] getOrderRouteSwitchingRows(String l_strInstitutionCode) throws WEB3BusinessLayerException, WEB3SystemLayerException;
  
    /**
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u����"SONAR�S��Q"��<BR>
     * ���R�[�h�����݂��邩�ǂ����`�F�b�N����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@param �t�����g����������敪�R�[�h - �t�����g����������敪�R�[�h<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪<BR>
     * @@param �����^�C�v - �����^�C�v<BR>
     * @@roseuid 42D24C6D02E2
     */
    public void validateSonarCheck(String l_strInstitutionCode, String l_strFrontExCode, 
                   String l_strFrontSystemCode, String l_strProductType) throws WEB3BusinessLayerException, WEB3SystemLayerException;

    /**
     * �����o�H�̐ؑ֏�����A�ċN�����������s����B<BR>
     * @@param ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@roseuid 42F2B0410278
     */
    public void executeSwitchTransactionIssue(WEB3AdminFrontRouteChangeCompleteRequest l_request) throws WEB3SystemLayerException;

    /**
     * �����̔�����ؑ�Rows��蔭������̈ꗗ���쐬����B<BR>
     * @@param ������ؑ�Rows - ������ؑ�Rows�^�z��B<BR>
     * @@return ��������[]<BR>
     * @@roseuid 42F7151B02D5
     */
    public WEB3AdminOrderRouteSwitchingInfo[] createSwitchRouteInfoList(OrderSwitchingRow[] l_switchRows);

    /**
     * ������ؑփe�[�u������f�[�^���������A���ʂ�ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@return List<BR>
     * @@roseuid 42F716D00134
     */
    public List getFrontSwitchRouteTarget(String l_strInstitutionCode) throws WEB3BaseException;

    /**
     * ���z�T�[�o���e�[�u������A���z�T�[�o�������擾���A�����������I�u�W�F�N�g�Ɋi�[����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@param �s��R�[�h - �s��R�[�h<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪<BR>
     * @@param �����^�C�v - �����^�C�v<BR>
     * @@param �����������N���X - �����������N���X�I�u�W�F�N�g<BR>
     * @@roseuid 42F7174D0150
     */
    public void getVitualServerCount(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode,String l_ProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfo) throws WEB3SystemLayerException;

    /**
     * ��Q���z�T�[�o�Ǘ��e�[�u������ؑ֎w�������敪�������n�̃��R�[�h���擾���A���ʂ�<BR>
     * �ԋp����B<BR>
     * <BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h - �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@param �����^�C�v - �����^�C�v�B<BR>
     * @@return List<BR>
     * @@roseuid 42E4A1EA0315
     */
    public List getSwitchRouteResRcord(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode ,String l_strProductType) throws WEB3SystemLayerException; 

    /**
     * ��Q���z�T�[�o�Ǘ��e�[�u������ؑ֎w�������敪���v���n�̃��R�[�h���擾���A���ʂ�<BR>
     * �ԋp����B<BR>
     * <BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h - �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@param �����^�C�v - �����^�C�v�B<BR>
     * @@return List<BR>
     * @@roseuid 42FC54A400A2
     */
    public List getSwitchRouteReqRcord(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode, String l_strProductType) throws WEB3SystemLayerException; 

    /**
     * ������ؑփe�[�u�����猻�����o�H���������A���ʂ�ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@param �s��R�[�h - �s��R�[�h<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪<BR>
     * @@param �����^�C�v - �����^�C�v<BR>
     * @@param �����������N���X - �����������N���X�I�u�W�F�N�g<BR>
     * @@return String<BR>
     * @@roseuid 42F71F6502D0
     */
    public String getNowOrderRoute(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode,String l_ProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfo) throws WEB3SystemLayerException;

    /**
     * �����L���[�e�[�u������A"�s���t�m�F�O"�A"�s���t�m�F��"�A"�s���t�m�F��"��<BR>
     * �����������擾���A���������I�u�W�F�N�g�Ɋi�[����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@param �s��R�[�h - �s��R�[�h<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪<BR>
     * @@param ���������I�u�W�F�N�g - ���������I�u�W�F�N�g<BR>
     * @@roseuid 42F7213602B5
     */
    public void getGrayOrder(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode, WEB3AdminFrontProcessNumberInfoUnit l_processInfo) throws WEB3SystemLayerException;

    /**
     * �f�[�^�R�[�h��ԋp����B<BR>
     * @@param �ؑ֋N���敪 - �ؑ֋N���敪<BR>
     * @@param �ؑ֏��������敪 - �ؑ֏��������敪<BR>
     * @@return String<BR>
     * @@roseuid 42F7255C0113
     */
    public String getDataCode(String l_strSwitchBootDiv,String l_changeStartDiv);

    /**
     * ������ؑփe�[�u����DEOS���R�[�h�̗L�����`�F�b�N����B<BR>
     * @@param �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪�B<BR>
     * @@param �����^�C�v�B<BR>
     * @@return java.lang.boolean<BR>
     * @@roseuid 42E46BB2019E
     */
    public boolean isFrontRoute(String l_strInstitutionCode, String l_strMarketCode, String l_frontSysDiv, 
        String l_strProductType) throws WEB3SystemLayerException;

    /**
     * �t�����g�����s��R�[�h����A�t�����g����������敪�R�[�h���擾����B<BR>
     * @@param �t�����g�����s��R�[�h - �t�����g�����s��R�[�h�B<BR>
     * @@return String<BR>
     * @@roseuid 42F710A40139
     */
   public String getFrontOrderExchangeCode(String l_strConvertMarketCode);

   /**
    * �t�����g�����s��R�[�h����A�t�����g�����V�X�e���敪���擾����B<BR>
    * @@param �t�����g�����s��R�[�h - �t�����g�����s��R�[�h�B<BR>
    * @@return String<BR>
    * @@roseuid 42F711C40131
    */
  public String getFrontSystemDiv(String l_strConvertMarketCode);

  /**
   * �����̎s��R�[�h�A�t�����g�����V�X�e���敪�R�[�h����A��ʕ\���p��<BR>
   * �s��R�[�h�ɕϊ����A�ԋp����B<BR>
   * @@param �s��R�[�h - �s��R�[�h�B<BR>
   * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
   * @@return String<BR>
   * @@roseuid 42F71618033C
   */
 public String getFrontOrderMarketCode(String l_strMarketCode, String l_strFrontSystemCode);

  /**
   * �敨OP��������L���[�e�[�u������A"�s���t�m�F�O"�A"�s���t�m�F��"�A<BR>
   * "�s���t�m�F��"�̒����������擾���A���������I�u�W�F�N�g�Ɋi�[����B<BR>
   * @@param l_institutionCode - �،���ЃR�[�h<BR>
   * @@param l_marketCode - �s��R�[�h<BR>
   * @@param l_frontSystemCode - �t�����g�����V�X�e���敪<BR>
   * @@param l_processInfoUnit - �����������<BR>
   * @@throws WEB3SystemLayerException
   */
 public void getIfoGrayOrder(String l_institutionCode, String l_marketCode, String l_frontSystemCode, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException;
 
 /**
  * ���[�U�f�[�^��ԋp����B    <BR>
  * @@param �ϊ��s��R�[�h - ��ʕ\���p�ϊ��s��R�[�h�B<BR>
  * @@param �����^�C�v�B<BR>
  * @@param �ؑ֋N���敪 - �ؑ֋N���敪�B<BR>
  * @@return String<BR>
  * @@roseuid 42F1E26B0138
  */
 public String getUserData(String l_strConvertMarketCode, String l_strProductType, String l_strChangeStartDiv);
 
}
@
