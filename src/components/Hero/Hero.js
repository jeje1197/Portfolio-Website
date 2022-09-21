import Link from 'next/link';
import React from 'react';

import { Section, SectionText, SectionTitle, SectionSubText } from '../../styles/GlobalComponents';
import Button from '../../styles/GlobalComponents/Button';
import { LeftSection } from './HeroStyles';

const Hero = () => (
  <Section row nopadding>
    <LeftSection>

      <SectionTitle main center>
        Hi,<br/>
        I'm {"<"}Joseph Evans{"/>"}
      </SectionTitle>
      <SectionText>
        <SectionSubText>
          Software engineer and web developer from the University of California,
          Santa Cruz. My goal is to leverage cutting-edge technology to provide a pleasant user-experience.
        </SectionSubText>
      </SectionText>

      <Button onClick={() => window.location = 'mailTo: Josephev4@gmail.com'}>Contact me!</Button>

    </LeftSection>
  </Section>
);

export default Hero;