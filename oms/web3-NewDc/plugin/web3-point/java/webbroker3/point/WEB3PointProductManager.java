head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g���i�}�l�[�W��(WEB3PointProductManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;

/**
 * (�|�C���g���i�}�l�[�W��)<BR>
 * �|�C���g���i�}�l�[�W���C���^�[�t�F�C�X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public interface WEB3PointProductManager extends Service 
{
    
    /**
     * (get�J�e�S���[)<BR>
     * �،���ЃR�[�h�A�J�e�S���[�ԍ�����A�|�C���g�J�e�S���[�I�u�W�F�N�g���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * 
     * @@return webbroker3.point.WEB3PointCategory
     * @@roseuid 418F2DE30136
     */
    public WEB3PointCategory getCategory(String l_strInstitutionCode, String l_strCategoryNo) throws WEB3BaseException;
    
    /**
     * (get�J�e�S���[)<BR>
     * �،���ЃR�[�h����A���̏،���Ђ������Ă���J�e�S���[�̔z����擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@return webbroker3.point.WEB3PointCategory[]
     * @@roseuid 418F32DF0202
     */
    public WEB3PointCategory[] getCategories(String l_strInstitutionCode) throws WEB3BaseException;
    
    /**
     * (get�i�i)<BR>
     * �،���ЃR�[�h�A�i�i�ԍ�����A�|�C���g�i�i�I�u�W�F�N�g���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium
     * @@roseuid 418F32DF0230
     */
    public WEB3PointPremium getPremium(String l_strInstitutionCode, String l_strPremiumNo) throws WEB3BaseException;
    
    /**
     * (get�i�i)<BR>
     * �،���ЃR�[�h�A�J�e�S���[�ԍ�����A�|�C���g�i�i�̔z����擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium[]
     * @@roseuid 418F2DEA000E
     */
    public WEB3PointPremium[] getPremiums(String l_strInstitutionCode, String l_strCategoryNo) throws WEB3BaseException;
    
    /**
     * (get�戵�\�i�i)<BR>
     * �،���ЃR�[�h�A�J�e�S���[�ԍ�����A<BR>���ݎ戵�\�ȃ|�C���g�i�i�̔z����擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium[]
     * @@roseuid 41A43AEB0085
     */
    public WEB3PointPremium[] getHandingPossiblePremium(String l_strInstitutionCode, String l_strCategoryNo) throws WEB3BaseException;
    
    /**
     * (validate�J�e�S���[���e)<BR>
     * �J�e�S���[���e�̃`�F�b�N���s���B<BR>
     * @@param l_strCategoryName - (�J�e�S���[��)<BR>
     * �J�e�S���[��<BR>
     * 
     * @@param l_strOutline - (�T�v)<BR>
     * �T�v<BR>
     * @@roseuid 4191857503D0
     */
    public void validateCategorySpec(String l_strCategoryName, String l_strOutline) throws WEB3BaseException;
    
    /**
     * (saveNew�J�e�S���[)<BR>
     * �V�K�̃J�e�S���[��ǉ�(insert)����B<BR>
     * @@param l_category - (�J�e�S���[)<BR>
     * �|�C���g�J�e�S���[�I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 41919F6B0272
     */
    public void saveNewCategory(WEB3PointCategory l_category, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (validate�����J�e�S���[���e)<BR>
     * ���������J�e�S���[���e�̃`�F�b�N���s���B<BR>
     * @@param l_strCategoryName - (�J�e�S���[��)<BR>
     * �J�e�S���[��<BR>
     * 
     * @@param l_strOutline - (�T�v)<BR>
     * �T�v<BR>
     * 
     * @@param l_category - (�J�e�S���[)<BR>
     * �|�C���g�J�e�S���[�I�u�W�F�N�g<BR>
     * @@roseuid 4191B21100DC
     */
    public void validateChangeCategorySpec(String l_strCategoryName, String l_strOutline, WEB3PointCategory l_category) throws WEB3BaseException;
    
    /**
     * (save�J�e�S���[)<BR>
     * �J�e�S���[���X�V(update)����B<BR>
     * @@param l_category - (�J�e�S���[)<BR>
     * �|�C���g�J�e�S���[�I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 4191B666020C
     */
    public void saveCategory(WEB3PointCategory l_category, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (validate�戵�i�i)<BR>
     * �����̏،���ЃR�[�h�A�J�e�S���[�ԍ��Ɋ֘A����i�i���戵���Ă��邩�ǂ���<BR>
     * ���`�F�b�N����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strCategoryNo - (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     * @@roseuid 4191C0D803B2
     */
    public void validateHandingPremium(String l_strInstitutionCode, String l_strCategoryNo) throws WEB3BaseException;
    
    /**
     * (validate�d���i�i�ԍ�)<BR>
     * �i�i�ԍ����d�����ĂȂ������`�F�b�N����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * @@roseuid 4193258A0250
     */
    public void validateDupliPremiumNo(String l_strInstitutionCode, String l_strPremiumNo) throws WEB3BaseException;
    
    /**
     * (validate�i�i���e)<BR>
     * �i�i�̓��e���`�F�b�N����B<BR>
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@param l_strPremiumName - (�i�i��)<BR>
     * �i�i��<BR>
     * 
     * @@param l_strRequiredPoint - (�K�v�|�C���g)<BR>
     * �K�v�|�C���g<BR>
     * 
     * @@param l_datStartDateTime - (�񋟊J�n����)<BR>
     * �񋟊J�n����<BR>
     * 
     * @@param l_datEndDateTime - (�񋟏I������)<BR>
     * �񋟏I������<BR>
     * @@roseuid 4193259E03C7
     */
    public void validatePremiumSpec(String l_strPremiumNo, String l_strPremiumName, String l_strRequiredPoint, Date l_datStartDateTime, Date l_datEndDateTime) throws WEB3BaseException;
    
    /**
     * (saveNew�i�i)<BR>
     * �V�K�̌i�i��ǉ�(insert)����B<BR>
     * @@param l_premium - (�i�i)<BR>
     * �|�C���g�i�i�I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 41933402031B
     */
    public void saveNewPremium(WEB3PointPremium l_premium, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (validate�����i�i���e)<BR>
     * ���������i�i�̓��e���`�F�b�N����B<BR>
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@param l_strPremiumName - (�i�i��)<BR>
     * �i�i��<BR>
     * @@param l_strRequiredPoint - (�K�v�|�C���g)<BR>
     * �K�v�|�C���g<BR>
     * 
     * @@param l_datStartDateTime - (�񋟊J�n����)<BR>
     * �񋟊J�n����<BR>
     * 
     * @@param l_datEndDateTime - (�񋟏I������)<BR>
     * �񋟏I������<BR>
     * 
     * @@param l_pointPremium - (�i�i)<BR>
     * �|�C���g�i�i�I�u�W�F�N�g<BR>
     * @@roseuid 419326D80108
     */
    public void validateChangePremiumSpec(String l_strPremiumNo, String l_strPremiumName, String l_strRequiredPoint, Date l_datStartDateTime, Date l_datEndDateTime, WEB3PointPremium l_pointPremium) throws WEB3BaseException;
    
    /**
     * (save�i�i)<BR>
     * �i�i���X�V(update)����B<BR>
     * @@param l_premium - (�i�i)<BR>
     * �|�C���g�i�i�I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419334320108
     */
    public void savePremium(WEB3PointPremium l_premium, WEB3Administrator l_admin) throws WEB3BaseException;
}
@
